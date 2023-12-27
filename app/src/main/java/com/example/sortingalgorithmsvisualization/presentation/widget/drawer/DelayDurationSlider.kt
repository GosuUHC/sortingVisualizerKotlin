package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel
import java.time.Duration

@Composable
fun DelayDurationSlider(vm: SortingViewModel = viewModel()) {
    val delay = vm.delay.value

    Log.i("RECOMPOSE", "Delay slider recompose")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Delay",
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            style = MaterialTheme.typography.titleLarge,
        )
        Slider(
            value = delay.toMillis().toFloat(),
            onValueChange = {
                vm.updateSelectedDelay(Duration.ofMillis(it.toLong()))
            },
            modifier = Modifier.weight(2f),
            valueRange = 5f..200f,
        )
    }
}
