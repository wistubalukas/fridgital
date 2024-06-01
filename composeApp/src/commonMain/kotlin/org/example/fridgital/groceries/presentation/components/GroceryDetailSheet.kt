package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.fridgital.core.presentation.CustomBottomSheet
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.presentation.GroceryListEvent

@Composable
fun GroceryDetailSheet(
    isOpen: Boolean,
    selectGrocery: Grocery?,
    onEvent: (GroceryListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    CustomBottomSheet(
        visible = isOpen,
        onClose = {
            onEvent(GroceryListEvent.DismissGrocery)
        },
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                GroceryPhoto(
                    grocery =  selectGrocery,
                    iconSize = 50.dp,
                    modifier = Modifier
                        .size(225.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "${selectGrocery?.title}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Row {
                    if (selectGrocery?.description != "") {
                        Text(
                            text = "${selectGrocery?.count} x",
                            modifier = Modifier
                                .padding(16.dp, 16.dp, 0.dp, 16.dp)
                        )
                        Text(
                            text = " ${selectGrocery?.description}",
                            modifier = Modifier
                                .padding(0.dp, 16.dp, 16.dp, 16.dp)
                        )
                    } else {
                        Text(
                            text = "${selectGrocery.count} x",
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                EditRow(
                    onEditClick = {
                        selectGrocery?.let {
                            onEvent(GroceryListEvent.EditGrocery(it))
                        }
                    },
                    onDeleteClick = {
                        selectGrocery?.let {
                            onEvent(GroceryListEvent.DeleteGrocery)
                        }
                    }
                )
            }
            IconButton(
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

@Composable
private fun EditRow(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        IconButton(
            onClick = onEditClick
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit",
            )
        }
        IconButton(
            onClick = onDeleteClick
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete",
                tint = Color(0xFFCD5C5C)
            )
        }
    }
}