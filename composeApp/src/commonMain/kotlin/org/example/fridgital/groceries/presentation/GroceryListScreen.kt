package org.example.fridgital.groceries.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.fridgital.core.presentation.ImagePicker
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.presentation.components.AddGrocerySheet
import org.example.fridgital.groceries.presentation.components.GroceryDetailSheet
import org.example.fridgital.groceries.presentation.components.GroceryListItem
import org.example.fridgital.groceries.presentation.components.ShoppingListSheet

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroceryListScreen(
    state: GroceryListState,
    newGrocery: Grocery?,
    onEvent: (GroceryListEvent) -> Unit,
    imagePicker: ImagePicker
) {
    imagePicker.registerPicker { imageBytes ->
        onEvent(GroceryListEvent.OnPhotoPicked(imageBytes))
    }
    Scaffold(
        floatingActionButton = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(72.dp)
                ) {

                    // FAB to add new digital grocery items
                    FloatingActionButton(
                        onClick = {
                            onEvent(GroceryListEvent.OnAddNewGroceryClick)
                        },
                        shape = RoundedCornerShape(24.dp),
                        backgroundColor = Color(0xFFEFEFEF),
                        contentColor = Color(0xFF333333),
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add grocery"
                        )
                    }

                    // counter in a bubble at the top left of the FAB
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(28.dp)
                            .background(Color(0xFF333333), shape = CircleShape)
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.groceries.size.toString(),
                            color = Color.White,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }

                Box(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .size(72.dp)
                ) {

                    // FAB to add new grocery shopping items
                    FloatingActionButton(
                        onClick = {
                            onEvent(GroceryListEvent.OnShoppingListClick)
                        },
                        shape = RoundedCornerShape(24.dp),
                        backgroundColor = Color(0xFFEFEFEF),
                        contentColor = Color(0xFF333333),
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.List,
                            contentDescription = "Add grocery shopping item"
                        )
                    }

                    // counter in a bubble at the top left of the FAB
                    /* TODO: adjust when shopping list is implemented
                        and fill counter in Text Composable dynamically
                     */
                    if (state.groceries.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .size(28.dp)
                                .background(Color(0xFF333333), shape = CircleShape)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${state.groceries.size}",
                                color = Color.White,
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // sticky header for the "Fridgital_" - Logo
            stickyHeader {
                Text(
                    text = "Fridgital_",
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(16.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            // grid for the digital fridge items
            itemsIndexed(state.groceries.chunked(3)) { _, row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp, 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    row.forEach { grocery ->
                        val interactionSource = remember { MutableInteractionSource() }
                        GroceryListItem(
                            grocery = grocery,
                            modifier = Modifier
                                .weight(1f)
                                .clickable (
                                    onClick = { onEvent(GroceryListEvent.EditGrocery(grocery)) },
                                    indication = null,
                                    interactionSource = interactionSource
                                )
                        )
                    }

                    // fill the empty spaces with empty boxes if the row is not full
                    if (row.size < 3) {
                        repeat(3 - row.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }

    GroceryDetailSheet(
        selectGrocery = state.selectedGrocery,
        isOpen = state.isSelectedGrocerySheetOpen,
        onEvent = onEvent
    )

    AddGrocerySheet(
        state = state,
        newGrocery = newGrocery,
        isOpen = state.isAddGrocerySheetOpen,
        onEvent = { event ->
            if (event is GroceryListEvent.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        }
    )

    ShoppingListSheet(
        state = state,
        newGrocery = newGrocery,
        isOpen = state.isShoppingListOpen,
        onEvent = onEvent,
        swipeToCloseEnabled = false
    )
}