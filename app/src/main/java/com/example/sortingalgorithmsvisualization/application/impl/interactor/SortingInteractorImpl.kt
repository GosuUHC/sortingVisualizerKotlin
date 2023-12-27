package com.example.sortingalgorithmsvisualization.application.impl.interactor

import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import com.example.sortingalgorithmsvisualization.application.api.internal.interactor.SortingInteractor
import com.example.sortingalgorithmsvisualization.application.impl.comparator.AsyncCallbackComparator
import com.example.sortingalgorithmsvisualization.application.impl.swapper.AsyncCallbackSwapper
import com.example.sortingalgorithmsvisualization.domain.api.external.Swapper
import com.example.sortingalgorithmsvisualization.domain.api.external.ValuesComparator
import com.example.sortingalgorithmsvisualization.domain.api.factory.SortingAlgorithmsFactory
import com.example.sortingalgorithmsvisualization.domain.api.internal.SortingAlgorithm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration

class SortingInteractorImpl : SortingInteractor {
    private var sortingAlgorithm: SortingAlgorithm? = null
    private var swapper: Swapper? = null
    private var comparator: ValuesComparator? = null
    private var delayDuration: Duration? = null
    private var isSorting: Boolean = false

    override suspend fun startSorting(
        algorithmType: AlgorithmType,
        input: MutableList<Int>,
        delay: Duration,
        onChangeCallback: () -> Unit,
        onCompareCallback: suspend (MutableList<Int>) -> Unit
    ) = withContext(Dispatchers.Default) {
        delayDuration = delay
        swapper = configureSwapper(onChangeCallback)
        comparator = configureComparator(onCompareCallback)

        updateAlgorithm(algorithmType)
        isSorting = true

        sortingAlgorithm!!.sort(input)
    }


    override fun pauseSorting() {
        isSorting = false
    }

    override fun resumeSorting() {
        isSorting = true
    }

    override fun updateDelay(delay: Duration) {
        this.delayDuration = delay
    }

    override fun reset() {
        sortingAlgorithm?.stopSorting()
    }

    private fun updateAlgorithm(algorithmType: AlgorithmType) {
        sortingAlgorithm = configureSortingAlgorithm(algorithmType)
    }

    private fun configureSwapper(onChangeCallback: () -> Unit): Swapper {
        return AsyncCallbackSwapper(onChangeCallback)
    }

    private fun configureComparator(onCompareCallback: suspend (MutableList<Int>) -> Unit): ValuesComparator {
        return AsyncCallbackComparator(onCompareCallback)
    }

    private fun configureSortingAlgorithm(algorithmType: AlgorithmType): SortingAlgorithm {
        val swapper = this.swapper!!
        val comparator = this.comparator!!

        return when (algorithmType) {
            AlgorithmType.BUBBLE -> SortingAlgorithmsFactory.createBubbleSort(
                swapper, comparator
            )

            AlgorithmType.QUICK -> SortingAlgorithmsFactory.createQuickSort(
                swapper, comparator
            )
        }
    }
}
