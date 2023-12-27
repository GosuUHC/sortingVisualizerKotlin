package com.example.sortingalgorithmsvisualization.domain.api.external

interface ValuesComparator {
    suspend fun compare(input: MutableList<Int>, i: Int, j: Int): Int
}