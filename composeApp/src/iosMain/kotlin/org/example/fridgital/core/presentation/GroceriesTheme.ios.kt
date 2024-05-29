package org.example.fridgital.core.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun GroceriesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content
    )
}