package com.example.sortingalgorithmsvisualization.presentation.widget.drawer

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sortingalgorithmsvisualization.presentation.widget.ColumnsCard
import com.example.sortingalgorithmsvisualization.presentation.widget.StartStopControlPanel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    Log.i("RERENDER", "drawer recompose")

    ModalNavigationDrawer(
        drawerContent = {

            ModalDrawerSheet(
                drawerContainerColor = Color.White.copy(alpha = 0.5f)
            ) {
                DrawerHeader()
                Divider()

                SortingAlgorithmSelect()
                ValueTypeSelect()
                ValueCountSlider()
                DelayDurationSlider()
                SaveButton()
            }
        },
        drawerState = drawerState,
        gesturesEnabled = true,
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Sorting Visualization",
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            },
                        ) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = null,
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                StartStopControlPanel()
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ColumnsCard()
            }
        }
    }
}
