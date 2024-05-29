package org.example.fridgital.di

import org.example.fridgital.groceries.domain.GroceryDataSource

expect class AppModule {
    val groceryDataSource: GroceryDataSource
}