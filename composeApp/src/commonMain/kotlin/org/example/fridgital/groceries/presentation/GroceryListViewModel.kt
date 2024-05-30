package org.example.fridgital.groceries.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.domain.GroceryDataSource
import org.example.fridgital.groceries.domain.GroceryValidator

class GroceryListViewModel(
    private val groceryDataSource: GroceryDataSource
): ViewModel() {

    private val  _state = MutableStateFlow(GroceryListState())
    val state = combine(
        _state,
        groceryDataSource.getGroceries(),
        groceryDataSource.getRecentGroceries(9)
    ) { state, groceries, recentGroceries ->
        state.copy(
            groceries = groceries,
            recentlyAddedGroceries = recentGroceries,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), GroceryListState())

    var newGrocery: Grocery? by mutableStateOf(null)
        private set

    fun onEvent(event: GroceryListEvent) {
        when(event) {
            GroceryListEvent.DeleteGrocery -> {
                viewModelScope.launch {
                    state.value.selectedGrocery?.id?.let { id ->
                        _state.update { it.copy(
                            isAddGrocerySheetOpen = false
                        ) }
                        groceryDataSource.deleteGrocery(id)
                        delay(300L)  // delay for animation
                        _state.update { it.copy(
                            selectedGrocery = null
                        ) }
                    }
                }
            }
            GroceryListEvent.DismissGrocery -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isSelectedGrocerySheetOpen = false,
                        isAddGrocerySheetOpen = false,
                        isShoppingListOpen = false,
                        titleError = null
                    ) }
                    delay(300L)
                    newGrocery = null
                    _state.update { it.copy(
                        selectedGrocery = null
                    ) }
                }
            }
            is GroceryListEvent.EditGrocery -> {
                _state.update { it.copy(
                    selectedGrocery = event.grocery,
                    isAddGrocerySheetOpen = true,
                    isSelectedGrocerySheetOpen = false
                ) }
                newGrocery = event.grocery
            }
            GroceryListEvent.OnAddNewGroceryClick -> {
                _state.update { it.copy(
                    isAddGrocerySheetOpen = true
                ) }
                newGrocery = Grocery(
                    id = null,
                    title = "",
                    description = "",
                    count = 1L,
                    photoBytes = null
                )
            }
            is GroceryListEvent.OnDescriptionChanged -> {
                newGrocery = newGrocery?.copy(
                    description = event.value
                )
            }
            is GroceryListEvent.OnPhotoPicked -> {
                newGrocery = newGrocery?.copy(
                    photoBytes = event.bytes
                )
            }
            is GroceryListEvent.OnTitleChanged -> {
                newGrocery = newGrocery?.copy(
                    title = event.value
                )
            }

            is GroceryListEvent.OnGroceryCountAdd -> {
                newGrocery = newGrocery?.copy(
                    count = event.value + 1L
                )
            }
            is GroceryListEvent.OnGroceryCountSub -> {
                newGrocery = newGrocery?.copy(
                    count = maxOf(1, event.value - 1L)
                )
            }

            GroceryListEvent.SaveGrocery -> {
                newGrocery?.let { grocery ->
                    val result = GroceryValidator.validateGrocery(grocery)
                    val errors = listOfNotNull(
                        result.tileError
                    )
                    if (errors.isEmpty()) {
                        _state.update { it.copy(
                            isAddGrocerySheetOpen = false,
                            titleError = null
                        ) }
                        viewModelScope.launch {
                            groceryDataSource.insertGrocery(grocery)
                            delay(300L)
                            newGrocery = null
                        }
                    } else {
                        _state.update { it.copy(
                            titleError = result.tileError
                        ) }
                    }
                }
            }
            is GroceryListEvent.SelectGrocery -> {
                _state.update { it.copy(
                    selectedGrocery = event.grocery,
                    isSelectedGrocerySheetOpen = true
                ) }
            }
            GroceryListEvent.OnShoppingListClick -> {
                _state.update { it.copy(
                    isShoppingListOpen = true
                ) }
                newGrocery = Grocery(
                    id = null,
                    title = "",
                    description = "",
                    count = 1L,
                    photoBytes = null
                )
            }
            else -> Unit
        }
    }
}