package com.example.sortingalgorithmsvisualization.application.impl.swapper

import com.example.sortingalgorithmsvisualization.domain.api.external.Swapper

class AsyncCallbackSwapper(private val onChangeCallback: () -> Unit) : Swapper {
    override suspend fun swap(input: MutableList<Int>, i: Int, j: Int) {
        onChangeCallback()

        val temp = input[i]
        input[i] = input[j]
        input[j] = temp
    }
}