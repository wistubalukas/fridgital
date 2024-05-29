package org.example.fridgital.groceries.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import org.example.fridgital.core.data.ImageStorage
import org.example.fridgital.database.GroceryDatabase
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.domain.GroceryDataSource

class SqlDelightGroceryDataSource(
    db: GroceryDatabase,
    private val imageStorage: ImageStorage
): GroceryDataSource {

    private val queries = db.groceryDatabaseQueries
    override fun getGroceries(): Flow<List<Grocery>> {
        return queries
            .getGroceries()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { groceryEntities ->
                supervisorScope {
                    groceryEntities
                        .map {
                            async { it.toGrocery(imageStorage) }
                        }
                        .map {
                            it.await()
                        }
                }
            }
    }

    override fun getRecentGroceries(amount: Int): Flow<List<Grocery>> {
        return queries
            .getRecentGroceries(amount.toLong())
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { groceryEntities ->
                supervisorScope {
                    groceryEntities
                        .map {
                            async { it.toGrocery(imageStorage) }
                        }
                        .map {
                            it.await()
                        }
                }
            }
    }

    override suspend fun insertGrocery(grocery: Grocery) {
        val imagePath = grocery.photoBytes?.let {
            imageStorage.saveImage(it)
        }
        queries.insertGroceryEntity(
            id = grocery.id,
            title = grocery.title,
            description = grocery.description,
            count = grocery.count,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = imagePath
        )
    }

    override suspend fun deleteGrocery(id: Long) {
        val entity = queries.getGroceryById(id).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        queries.deleteGroceryById(id)
    }
}