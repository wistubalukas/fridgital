package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.fridgital.groceries.domain.Grocery

@Composable
fun GroceryPreviewItem(
    grocery: Grocery,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
//        modifier = modifier
//            .clickable (onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GroceryPhoto(
            grocery = grocery,
            modifier = Modifier
                .size(100.dp)
        )
        Spacer(Modifier.height(0.dp))
        Text(
            text = grocery.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(100.dp)
                .padding(4.dp)
        )
    }
}