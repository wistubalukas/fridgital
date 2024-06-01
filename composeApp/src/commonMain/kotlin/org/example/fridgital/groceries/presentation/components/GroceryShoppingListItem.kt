package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.fridgital.groceries.domain.Grocery
import org.example.fridgital.groceries.presentation.GroceryListEvent

@Composable
fun GroceryShoppingListItem(
    grocery: Grocery,
    onEvent: (GroceryListEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .background(Color(0xFFF7F7F7))
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(100.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // image
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .size(100.dp)
        ) {
            GroceryPhoto(
                grocery = grocery,
                modifier = Modifier
                    .fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .offset(x = (-10).dp, y = (-10).dp)
                    .align(Alignment.TopStart)
                    .size(28.dp)
                    .background(Color(0xFF333333), shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${grocery.count}",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                )
            }
        }
        // title and description
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFEFEFEF),
                        shape = RoundedCornerShape(15)
                    )
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = grocery.title,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF333333),
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
            Box(
                modifier = Modifier
                    .weight(.5f)
                    .fillMaxSize()
                    .background(
                        color = Color(0xFFEFEFEF),
                        shape = RoundedCornerShape(15)
                    )
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = grocery.description,
                    color = Color(0xFF333333),
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                )
            }
        }
        // add and sub buttons
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    onEvent(GroceryListEvent.DismissGrocery)
                },
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = Color(0xFFEFEFEF),
                        shape = RoundedCornerShape(15)
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Addieren",
                    tint = Color(0xFF333333),
                )
            }
            IconButton(
                onClick = {
                    onEvent(GroceryListEvent.DismissGrocery)
                },
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = Color(0xFFEFEFEF),
                        shape = RoundedCornerShape(15)
                    )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Remove,
                    contentDescription = "Subtrahieren",
                    tint = Color(0xFF333333)
                )
            }
        }
        // save or edit button
        IconButton(
            onClick = {
                onEvent(GroceryListEvent.DismissGrocery)
            },
            modifier = Modifier
                .padding(start = 24.dp, end = 16.dp)
                .width(16.dp)
                .fillMaxHeight()
                .background(
                    color = Color(0xFFEFEFEF),
                    shape = RoundedCornerShape(15)
                )
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Addieren",
                tint = Color(0xFF333333),
            )
        }
    }
}