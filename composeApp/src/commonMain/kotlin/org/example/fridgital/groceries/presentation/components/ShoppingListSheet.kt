package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.fridgital.core.presentation.CustomBottomSheet
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.presentation.GroceryListEvent
import org.example.fridgital.groceries.presentation.GroceryListState

@Composable
fun ShoppingListSheet(
    state: GroceryListState,
    newGrocery: Grocery?,
    isOpen: Boolean,
    onEvent: (GroceryListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomBottomSheet(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(70.dp))
                // TODO: change to LazyColumn with shopping list items (groceries with inFridge = false)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (state.groceries.isNotEmpty()) {
                        // item {
                            GroceryShoppingListItem(
                                state.groceries[0],
                                onEvent
                            )
                            GroceryShoppingListItem(
                                state.groceries[1],
                                onEvent
                            )
                        // }
                    }
                }
                // Add new grocery to shopping list button
                IconButton(
                    onClick = {
                        onEvent(GroceryListEvent.DismissGrocery)
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
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