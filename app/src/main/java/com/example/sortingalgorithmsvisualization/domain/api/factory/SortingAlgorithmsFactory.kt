package com.example.sortingalgorithmsvisualization.domain.api.factory

import com.example.sortingalgorithmsvisualization.domain.api.external.Swapper
import com.example.sortingalgorithmsvisualization.domain.api.external.ValuesComparator
import com.example.sortingalgorithmsvisualization.domain.api.internal.SortingAlgorithm
import com.example.sortingalgorithmsvisualization.domain.impl.algorithm.BubbleSort
import com.example.sortingalgorithmsvisualization.domain.impl.algorithm.QuickSort

object SortingAlgorithmsFactory {
    fun createBubbleSort(swapper: Swapper, comparator: ValuesComparator): SortingAlgorithm =
        BubbleSort(swapper, comparator)

    fun createQuickSort(swapper: Swapper, comparator: ValuesComparator): SortingAlgorithm =
        QuickSort(swapper, comparator)
}