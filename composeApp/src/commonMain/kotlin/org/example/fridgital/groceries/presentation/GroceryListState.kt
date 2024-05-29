package org.example.fridgital.groceries.presentation

import org.example.fridgital.groceries.domain.Grocery

data class GroceryListState(
    val groceries: List<Grocery> = emptyList(),
    val recentlyAddedGroceries: List<Grocery> = emptyList(),
    val selectedGrocery: Grocery? = null,
    val isSelectedGrocerySheetOpen: Boolean = false,
    val isAddGrocerySheetOpen: Boolean = false,
    val titleError: String? = null,
    val isShoppingListOpen: Boolean = false
)
