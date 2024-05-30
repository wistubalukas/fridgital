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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.height(60.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (newGrocery != null) {
                            if (newGrocery.count > 1) {
                                // decrement count
                                IconButton(
                                    onClick = {
                                        onEvent(GroceryListEvent.OnGroceryCountSub(newGrocery.count))
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
                                        tint = Color(0xFF333333)
                                    )
                                }
                            } else {
                                // delete grocery
                                IconButton(
                                    onClick = {
                                        onEvent(GroceryListEvent.DeleteGrocery)
                                    },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(
                                            color = Color(0xFFF1C0C0),
                                            shape = RoundedCornerShape(24.dp)
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Delete,
                                        contentDescription = "Delete",
                                        tint = Color(0xFFCD5C5C)
                                    )
                                }
                            }
                        }

                        Box(
                            contentAlignment = Alignment.TopStart
                        ) {
                            if (newGrocery?.photoBytes == null) {
                                Box(
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(15))
                                        .background(Color(0xFFEFEFEF))
                                        .clickable {
                                            onEvent(GroceryListEvent.OnAddPhotoClicked)
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Add,
                                        contentDescription = "Bild hinzufügen",
                                        tint = Color(0xFF333333),
                                        modifier = Modifier.size(40.dp)
                                    )
                                }
                            } else {
                                GroceryPhoto(
                                    grocery = newGrocery,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clickable {
                                            onEvent(GroceryListEvent.OnAddPhotoClicked)
                                        }
                                )
                            }
                            GroceryDetailsBubble(newGrocery)
                        }
                        // increment count
                        IconButton(
                            onClick = {
                                if (newGrocery != null) {
                                    onEvent(GroceryListEvent.OnGroceryCountAdd(newGrocery.count))
                                }
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
                                tint = Color(0xFF333333)
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
                    Row{
                        Button(
                            onClick = {
                                onEvent(GroceryListEvent.SaveGrocery)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFEFEFEF))
                        ) {
                            Text(
                                text = "Speichern",
                                color = Color(0xFF333333)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = {
                                onEvent(GroceryListEvent.DismissGrocery)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1C0C0))
                        ) {
                            Text(
                                text = "Verwerfen",
                                color = Color(0xFFCD5C5C)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(40.dp))
                RecentlyAddedGroceries(
                    groceries = state.recentlyAddedGroceries,
                    onClick = {
                        onEvent(GroceryListEvent.SelectGrocery(it))
                    }
                )
            }
            IconButton(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    onEvent(GroceryListEvent.DismissGrocery)
                }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Schließen",
                    tint = Color(0xFF333333)
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
                color = Color(0xFFCD5C5C),
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(16.dp, 4.dp)
            )
        }
    }
}

@Composable
private fun GroceryDetailsBubble(
    newGrocery: Grocery?
) {
    Box(
        modifier = Modifier
            .offset(x = (-10).dp, y = (-10).dp)
            .height(28.dp)
            .widthIn(min = 28.dp, max = 140.dp)
            .background(Color(0xFF333333), shape = CircleShape)
            .padding(horizontal = 6.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row {
            if (newGrocery?.description != "") {
                // TODO: enable when counter logic is implemented
                // if (grocery.count > 1) {
                Text(
                    text = "${newGrocery?.count} x",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                )
                // }
                Text(
                    text = " ${newGrocery?.description}",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                )
            } else {
                Text(
                    text = "${newGrocery.count}",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                )
            }
        }
    }
}