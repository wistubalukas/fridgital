package org.example.fridgital.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomSheet(
    visible: Boolean,
    swipeEnabled: Boolean = true,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    // Scroll detection for swipe to close
    var cumulativeDrag by remember { mutableStateOf(0f) }
    val dragThreshold = 80f

    val nestedScrollConnection = remember(swipeEnabled) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (swipeEnabled && available.y > 0) {
                    cumulativeDrag += available.y
                    if (cumulativeDrag > dragThreshold) {
                        onClose()
                        cumulativeDrag = 0f
                    }
                }
                return super.onPreScroll(available, source)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (swipeEnabled && consumed.y.toInt() == 0 && available.y > 0) {
                    cumulativeDrag = 0f  // Reset the cumulative drag when the user stops dragging
                }
                return super.onPostScroll(consumed, available, source)
            }
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 300),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 300),
            targetOffsetY = { it }
        )
    ) {
        Column(
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp
                    )
                )
                .background(Color.White)
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(rememberScrollState())
        ) {
            content()
        }
    }
}