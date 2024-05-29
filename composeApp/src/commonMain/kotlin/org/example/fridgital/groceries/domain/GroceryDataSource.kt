package org.example.fridgital.groceries.domain

import kotlinx.coroutines.flow.Flow

interface GroceryDataSource {
    fun getGroceries(): Flow<List<Grocery>>
    fun getRecentGroceries(amount: Int): Flow<List<Grocery>>
    suspend fun insertGrocery(grocery: Grocery)
    suspend fun deleteGrocery(id: Long)
}