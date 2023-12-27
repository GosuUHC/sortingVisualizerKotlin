package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel

@Composable
fun SortingAlgorithmSelect(vm: SortingViewModel = viewModel()) {
    var expanded by remember { mutableStateOf(false) }
    val algorithmType = vm.algorithmType.value

    Log.i("RERENDER", "sorting algorithm select recompose")

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Sorting algorithm",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Choose algorithm",
                style = MaterialTheme.typography.titleSmall,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopEnd)
        ) {
            Text(
                text = algorithmType.name,
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(12.dp),
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                AlgorithmType.values().forEach { algorithmType ->
                    DropdownMenuItem(
                        text = { Text(algorithmType.name) },
                        onClick = {
                            vm.updateSelectedAlgorithm(algorithmType)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}