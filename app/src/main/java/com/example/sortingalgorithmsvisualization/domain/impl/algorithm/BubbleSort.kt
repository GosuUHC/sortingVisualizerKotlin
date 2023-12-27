package com.example.sortingalgorithmsvisualization.domain.impl.algorithm

import android.util.Log
import com.example.sortingalgorithmsvisualization.domain.api.external.Swapper
import com.example.sortingalgorithmsvisualization.domain.api.external.ValuesComparator
import com.example.sortingalgorithmsvisualization.domain.api.internal.SortingAlgorithm

class BubbleSort(
    private val swapper: Swapper,
    private val comparator: ValuesComparator,
) : SortingAlgorithm {
    private var isComplete: Boolean = false

    override suspend fun sort(input: MutableList<Int>) {
        val length = input.size
        for (i in 0 until length - 1) {
            var swapped = false

            for (j in 0 until length - 1 - i) {
                if (isComplete) {
                    Log.i("COMPLETE", "RETURNING")
                    return
                }

                if (comparator.compare(input, j, j + 1) > 0) {
                    swapper.swap(input, j, j + 1)
                    swapped = true
                }
            }

            if (!swapped) {
                break
            }
        }
    }

    override fun stopSorting() {
        isComplete = true
    }
}