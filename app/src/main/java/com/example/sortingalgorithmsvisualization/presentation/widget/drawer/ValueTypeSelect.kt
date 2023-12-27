package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.application.ValueType
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel


@Composable
fun ValueTypeSelect(vm: SortingViewModel = viewModel()) {
    val valueType = vm.valueType.value
    var expanded by remember { mutableStateOf(false) }

    Log.i("RERENDER", "value type select recompose")

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Value Generation Type",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Choose how values are generated",
                style = MaterialTheme.typography.titleSmall,
            )
        }

        Box {
            Text(
                text = valueType.name,
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(12.dp),
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                ValueType.values().forEach { valueType ->
                    DropdownMenuItem(
                        text = { Text(valueType.name) },
                        onClick = {
                            vm.updateSelectedValueType(valueType)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}
