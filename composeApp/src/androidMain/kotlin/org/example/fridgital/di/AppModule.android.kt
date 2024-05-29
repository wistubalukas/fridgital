package org.example.fridgital.di

import android.content.Context
import org.example.fridgital.core.data.DatabaseDriverFactory
import org.example.fridgital.core.data.ImageStorage
import org.example.fridgital.database.GroceryDatabase
import org.example.fridgital.groceries.data.SqlDelightGroceryDataSource
import org.example.fridgital.groceries.domain.GroceryDataSource

actual class AppModule(
    private val context: Context
) {
    actual val groceryDataSource: GroceryDataSource by lazy {
        SqlDelightGroceryDataSource(
            db = GroceryDatabase(
                driver = DatabaseDriverFactory(context).create()
            ),
            imageStorage = ImageStorage(context)
        )
    }
}