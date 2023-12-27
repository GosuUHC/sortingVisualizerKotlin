package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerHeader() {
    Log.i("RERENDER", "drawer header recompose")

    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Sorting options",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }
}
