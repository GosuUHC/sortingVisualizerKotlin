package com.example.sortingalgorithmsvisualization.presentation.screen

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.sortingalgorithmsvisualization.presentation.widget.drawer.Drawer

@Composable
fun SortingScreen() {
    Log.i("RERENDER", "sorting screen recompose")
    Drawer()
}
