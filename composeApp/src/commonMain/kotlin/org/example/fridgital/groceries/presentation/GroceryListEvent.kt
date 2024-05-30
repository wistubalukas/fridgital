package org.example.fridgital.groceries.presentation

import org.example.fridgital.groceries.domain.Grocery

sealed interface GroceryListEvent {
    object OnAddNewGroceryClick: GroceryListEvent
    object DismissGrocery: GroceryListEvent
    data class OnTitleChanged(val value: String): GroceryListEvent
    data class OnDescriptionChanged(val value: String): GroceryListEvent
    class OnPhotoPicked(val bytes: ByteArray): GroceryListEvent
    object OnAddPhotoClicked: GroceryListEvent
    object SaveGrocery: GroceryListEvent
    data class SelectGrocery(val grocery: Grocery) : GroceryListEvent
    data class EditGrocery(val grocery: Grocery) : GroceryListEvent
    object DeleteGrocery: GroceryListEvent
    object OnShoppingListClick: GroceryListEvent
    data class OnGroceryCountAdd(val value: Long): GroceryListEvent
    data class OnGroceryCountSub(val value: Long): GroceryListEvent
}