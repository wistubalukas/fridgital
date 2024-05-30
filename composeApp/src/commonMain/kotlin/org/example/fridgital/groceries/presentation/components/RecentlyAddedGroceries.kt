package org.example.fridgital.groceries.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.fridgital.groceries.domain.Grocery

@Composable
fun RecentlyAddedGroceries(
    groceries: List<Grocery>,
    onClick: (Grocery) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (groceries.isNotEmpty()) {
            Text(
                text = "Zuletzt hinzugefügt",
                color = Color(0xFF333333),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 16.dp),
                fontWeight = FontWeight.Bold
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(groceries.take(9)) { grocery ->
                GroceryPreviewItem(
                    grocery = grocery,
                    modifier = Modifier,
                    onClick = { onClick(grocery) }
                )
            }
        }
    }
}