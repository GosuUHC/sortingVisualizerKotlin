package com.example.sortingalgorithmsvisualization.application.api.internal.interactor

import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import java.time.Duration

interface SortingInteractor {
    suspend fun startSorting(
        algorithmType: AlgorithmType,
        input: MutableList<Int>,
        delay: Duration,
        onChangeCallback: () -> Unit,
        onCompareCallback: suspend (MutableList<Int>) -> Unit,
    )

    fun pauseSorting()
    fun resumeSorting()
    fun updateDelay(delay: Duration)
    fun reset()
}