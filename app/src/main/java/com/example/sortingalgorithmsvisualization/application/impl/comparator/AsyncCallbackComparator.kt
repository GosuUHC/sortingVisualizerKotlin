package com.example.sortingalgorithmsvisualization.application.impl.comparator

import com.example.sortingalgorithmsvisualization.domain.api.external.ValuesComparator

class AsyncCallbackComparator(private val onCompareCallback: suspend (MutableList<Int>) -> Unit) :
    ValuesComparator {
    override suspend fun compare(input: MutableList<Int>, i: Int, j: Int): Int {
        onCompareCallback(mutableListOf<Int>(i, j))

        return input[i].compareTo(input[j])
    }
}
