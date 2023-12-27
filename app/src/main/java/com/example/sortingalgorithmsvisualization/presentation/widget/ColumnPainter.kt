package com.example.sortingalgorithmsvisualization.presentation.widget

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel


@Composable
fun ColumnPainter(vm: SortingViewModel = viewModel()) {
    val sortedData = vm.sortedData.value
    val comparingIndices = vm.comparingIndices.value

    Log.i("RERENDER", "column painter recompose")
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density
    val canvasWidth = (configuration.screenWidthDp * density).toInt()
    val canvasHeight = (configuration.screenHeightDp * density / 1.8).toInt()

    Canvas(
        modifier = Modifier.size(canvasWidth.dp, canvasHeight.dp)
    ) {
        val elementWidth = size.width / sortedData.size

        val maxElementHeight = sortedData.takeIf { it.isNotEmpty() }?.let {
            it.maxOrNull()?.toFloat() ?: 1.0f
        } ?: 1.0f

        val colors = mutableListOf(
            Color(255, 235, 59, 255), // yellow
            Color(0, 188, 212, 255), // cyan
            Color(255, 193, 7, 255), // amber
        )

        val defaultColor = Color(124, 77, 255, 255) // purple

        sortedData.forEachIndexed { i, value ->
            val elementHeight = value.toFloat() / maxElementHeight * size.height
            val rect = Rect(
                left = i.toFloat() * elementWidth,
                top = size.height,
                right = (i + 1f) * elementWidth,
                bottom = size.height - elementHeight
            )

            val color = when {
                comparingIndices.contains(i) -> colors.removeLast()
                else -> defaultColor
            }

            drawRect(
                color = color,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width, rect.height),
            )

            // Drawing rect twice for fill and visible border
            drawRect(
                color = Color.Black,
                topLeft = Offset(rect.left, rect.top),
                size = Size(rect.width, rect.height),
                style = Stroke(width = 1f)
            )
        }
    }
}
