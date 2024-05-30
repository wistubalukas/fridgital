package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.fridgital.core.presentation.rememberBitmapFromBytes
import org.example.fridgital.groceries.domain.Grocery

@Composable
fun GroceryPhoto(
    grocery: Grocery?,
    modifier: Modifier = Modifier,
    iconSize: Dp = 25.dp
) {
    val bitmap = rememberBitmapFromBytes(grocery?.photoBytes)
    val photoModifier = modifier.clip(RoundedCornerShape(15))

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = grocery?.title,
            modifier = photoModifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = photoModifier
                .background(Color(0xFF999999)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Image,
                contentDescription = grocery?.title,
                modifier = Modifier.size(iconSize),
                tint = Color.White
            )
        }
    }
}