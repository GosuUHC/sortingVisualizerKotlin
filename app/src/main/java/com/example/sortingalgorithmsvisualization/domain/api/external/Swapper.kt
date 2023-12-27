package com.example.sortingalgorithmsvisualization.domain.api.external

interface Swapper {
    suspend fun swap(input: MutableList<Int>, i: Int, j: Int)
}