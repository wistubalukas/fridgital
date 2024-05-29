package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Remove
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
fun AddGrocerySheet(
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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            onEvent(GroceryListEvent.DismissGrocery)
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Remove,
                            contentDescription = "Subtrahieren",
                            tint = Color.Black
                        )
                    }

                    if (newGrocery?.photoBytes == null) {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(15))
                                .background(Color.LightGray)
                                .clickable {
                                    onEvent(GroceryListEvent.OnAddPhotoClicked)
                                }
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFF999999),
                                    shape = RoundedCornerShape(15)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Bild hinzufügen",
                                tint = Color.Black,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    } else {
                        GroceryPhoto(
                            grocery =  newGrocery,
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    onEvent(GroceryListEvent.OnAddPhotoClicked)
                                }
                        )
                    }

                    IconButton(
                        onClick = {
                            onEvent(GroceryListEvent.DismissGrocery)
                        },
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(0xFFEFEFEF),
                                shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Addieren",
                            tint = Color.Black
                        )
                    }
                }
                Spacer(modifier.height(42.dp))
                GroceryTextField(
                    value = newGrocery?.title ?: "",
                    placeholder = "z.B. Nudeln",
                    error = state.titleError,
                    onValueChanged = {
                        onEvent(GroceryListEvent.OnTitleChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier.height(16.dp))
                GroceryTextField(
                    value = newGrocery?.description ?: "",
                    placeholder = "z.B. 500g",
                    error = null,
                    onValueChanged = {
                        onEvent(GroceryListEvent.OnDescriptionChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier.height(16.dp))
                Row(

                ) {
                    Button(
                        onClick = {
                            onEvent(GroceryListEvent.SaveGrocery)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF777777))
                    ) {
                        Text(
                            text = "Speichern",
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            onEvent(GroceryListEvent.DismissGrocery)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFCD5C5C))
                    ) {
                        Text(
                            text = "Abbrechen",
                            color = Color.White
                        )
                    }
                }
                Spacer(Modifier.height(60.dp))
                RecentlyAddedGroceries(
                    groceries = state.recentlyAddedGroceries,
                    onClick = {
                        onEvent(GroceryListEvent.SelectGrocery(it))
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
                    contentDescription = "Schließen"
                )
            }
        }
    }
}

@Composable
private fun GroceryTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
            )
        }
    }
}