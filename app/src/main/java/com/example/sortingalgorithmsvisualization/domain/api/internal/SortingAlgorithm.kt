package com.example.sortingalgorithmsvisualization.domain.api.internal

interface SortingAlgorithm {
    suspend fun sort(input: MutableList<Int>)

    fun stopSorting()
}
