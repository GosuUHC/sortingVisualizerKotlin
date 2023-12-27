package com.example.sortingalgorithmsvisualization.domain.impl.algorithm

import com.example.sortingalgorithmsvisualization.domain.api.external.Swapper
import com.example.sortingalgorithmsvisualization.domain.api.external.ValuesComparator
import com.example.sortingalgorithmsvisualization.domain.api.internal.SortingAlgorithm

class QuickSort(
    private val swapper: Swapper,
    private val comparator: ValuesComparator,
) : SortingAlgorithm {
    private var isComplete: Boolean = false

    override suspend fun sort(input: MutableList<Int>) {
        quickSort(input, 0, input.size - 1)
    }

    private suspend fun quickSort(input: MutableList<Int>, low: Int, high: Int) {
        if (low >= high || isComplete) {
            return
        }

        val pivotIndex = partition(input, low, high)

        quickSort(input, low, pivotIndex - 1)
        quickSort(input, pivotIndex + 1, high)
    }

    private suspend fun medianOfThree(
        input: MutableList<Int>, low: Int, middle: Int, high: Int
    ): Int {
        val a = comparator.compare(input, low, middle)
        val b = comparator.compare(input, middle, high)
        val c = comparator.compare(input, low, high)

        return when {
            a <= 0 && b <= 0 -> middle
            a <= 0 && c <= 0 -> high
            else -> low
        }
    }

    private suspend fun partition(input: MutableList<Int>, low: Int, high: Int): Int {
        val middle = (low + high) / 2
        val pivotIndex = medianOfThree(input, low, middle, high)

        swapper.swap(input, pivotIndex, middle)

        var i = low - 1

        for (j in low until high) {
            if (comparator.compare(input, j, high) <= 0) {
                i++
                swapper.swap(input, i, j)
            }
        }

        swapper.swap(input, i + 1, high)
        return i + 1
    }

    override fun stopSorting() {
        isComplete = true
    }
}
