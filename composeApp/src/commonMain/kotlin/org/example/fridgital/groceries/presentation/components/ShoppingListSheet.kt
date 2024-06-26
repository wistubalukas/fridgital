package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.fridgital.core.presentation.CustomBottomSheet
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.presentation.GroceryListEvent
import org.example.fridgital.groceries.presentation.GroceryListState

@Composable
fun ShoppingListSheet(
    state: GroceryListState,
    newGrocery: Grocery?,
    isOpen: Boolean,
    swipeToCloseEnabled: Boolean,
    onEvent: (GroceryListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomBottomSheet(
        visible = isOpen,
        onClose = {
            onEvent(GroceryListEvent.DismissGrocery)
        },
        swipeEnabled = swipeToCloseEnabled,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(72.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item { Spacer(Modifier.height(8.dp)) }
                    items(state.groceries) { grocery ->
                        GroceryShoppingListItem(
                            grocery,
                            onEvent
                        )
                    }
                    // space to the bottom for the add button
                    item { Spacer(Modifier.height(72.dp)) }
                }
            }
            // Add new grocery to shopping list component
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(Color.White)
                    .padding(vertical = 16.dp)
            ) {
                /* todo: add form to create a new shopping list grocery item
                    which will be expanded from bottom to top when clicking on the add button
                 */
                // add button
                IconButton(
                    onClick = {
                        onEvent(GroceryListEvent.DismissGrocery)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15))
                        .background(
                            color = Color(0xFFEFEFEF)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Add grocery to shopping list",
                        tint = Color(0xFF333333)
                    )
                }
            }
            // close sheet button
            IconButton(
                modifier = Modifier.padding(0.dp, 16.dp),
                onClick = {
                    onEvent(GroceryListEvent.DismissGrocery)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}