package com.example.sortingalgorithmsvisualization.presentation.widget

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sortingalgorithmsvisualization.presentation.state.SortingStatus
import com.example.sortingalgorithmsvisualization.presentation.state.SortingViewModel

@Composable
fun StartStopControlPanel(vm: SortingViewModel = viewModel()) {
    val sortingStatus = vm.sortingStatus.value

    Log.i("RERENDER", "startStop control panel recompose")

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(72.dp),
        horizontalArrangement = Arrangement.End
    ) {
        FloatingActionButton(
            onClick = {
                vm.onPressPlayButton()
            },
            shape = CircleShape,
        ) {
            val iconData = when (sortingStatus) {
                SortingStatus.RUNNING -> Icons.Default.Close
                else -> Icons.Default.PlayArrow
            }

            Icon(
                imageVector = iconData,
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        FloatingActionButton(
            onClick = {
                vm.reset()
            },
            shape = CircleShape,
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
            )
        }
    }
}
