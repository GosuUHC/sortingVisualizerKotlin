package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel

@Composable
fun SaveButton(vm: SortingViewModel = viewModel()) {
    val shouldSaveSettings = vm.shouldSaveSettings.value

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Save settings",
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            style = MaterialTheme.typography.titleLarge,
        )
        IconButton(onClick = {
            vm.updateShouldSaveSettings(!shouldSaveSettings)
        }) {
            GetIconDrawable(shouldSaveSettings)
        }
    }
}

@Composable
fun GetIconDrawable(shouldSaveSettings: Boolean) {
    if (shouldSaveSettings) {
        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.Green)
    } else {
        Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.Blue)
    }
}