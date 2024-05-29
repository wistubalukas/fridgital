package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.fridgital.groceries.domain.Grocery

@Composable
fun GroceryListItem(
    grocery: Grocery,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                GroceryPhoto(
                    grocery = grocery,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .offset(x = (-10).dp, y = (-10).dp)
                    .align(Alignment.TopStart)
                    .height(28.dp)
                    .widthIn(min = 28.dp, max = 112.dp)
                    .background(Color.Black, shape = CircleShape)
                    .padding(horizontal = 6.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row {
                    if (grocery.description != "") {
                        // TODO: enable when counter logic is implemented
                        // if (grocery.count > 1) {
                            Text(
                                text = "${grocery.count} x",
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White,
                                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            )
                        // }
                        Text(
                            text = " ${grocery.description}",
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        )
                    } else {
                        Text(
                            text = "${grocery.count}",
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White,
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }
        }
        Text(
            text = grocery.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .height(38.dp)
        )
    }
}