package com.example.sortingalgorithmsvisualization.presentation.state

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import com.example.sortingalgorithmsvisualization.application.ValueType
import com.example.sortingalgorithmsvisualization.application.api.external.repository.SortingRepository
import com.example.sortingalgorithmsvisualization.application.api.internal.interactor.SortingInteractor
import com.example.sortingalgorithmsvisualization.application.api.internal.interactor.ValuesInteractor
import com.example.sortingalgorithmsvisualization.application.impl.interactor.SortingInteractorImpl
import com.example.sortingalgorithmsvisualization.application.impl.interactor.ValuesInteractorImpl
import com.example.sortingalgorithmsvisualization.infrastructure.repository.SortingRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration

class SortingViewModel(application: Application) : AndroidViewModel(application) {

    private val sortingInteractor: SortingInteractor = SortingInteractorImpl()
    private val valuesInteractor: ValuesInteractor = ValuesInteractorImpl()
    private val repository: SortingRepository = SortingRepositoryImpl(
        preferences = application.getSharedPreferences(
            "SortingPrefs", Context.MODE_PRIVATE
        )
    )

    val algorithmType = mutableStateOf(AlgorithmType.BUBBLE)
    val valueType = mutableStateOf(ValueType.RANDOM)
    val length = mutableStateOf(50)
    val delay = mutableStateOf(Duration.ofMillis(30))
    val initialData = mutableStateOf(mutableListOf<Int>())
    val sortedData = mutableStateOf(mutableListOf<Int>())
    val comparingIndices = mutableStateOf(emptyList<Int>())
    val sortingStatus = mutableStateOf(SortingStatus.INITIAL)
    val shouldSaveSettings = mutableStateOf(false)
    var onSwapElements: (() -> Unit) = {}
    var onCompareElements: (suspend (MutableList<Int>) -> Unit) = {}

    init {
        viewModelScope.launch {
            val repoShouldSaveSettings = repository.getShouldSaveSettings()
            if (repoShouldSaveSettings) {
                algorithmType.value = repository.getAlgorithmType()
                valueType.value = repository.getValueType()
                length.value = repository.getLength()
                delay.value = repository.getDelay()
                shouldSaveSettings.value = repoShouldSaveSettings
            }

            generateInitialValues()
        }
    }

    private fun generateInitialValues() {
        val values = valuesInteractor.getValues(valueType.value, length.value)

        initialData.value = values
        sortedData.value = values.toMutableList()
    }

    fun updateSelectedAlgorithm(algorithmType: AlgorithmType) {
        this.algorithmType.value = algorithmType
        sortingStatus.value = SortingStatus.INITIAL

        sortingInteractor.reset()
        saveToRepository()
    }

    fun updateSelectedDelay(delay: Duration) {
        this.delay.value = delay

        sortingInteractor.updateDelay(this.delay.value)
        saveToRepository()
    }

    fun updateSelectedValueType(valueType: ValueType) {
        this.valueType.value = valueType

        generateInitialValues()
        reset()
        saveToRepository()
    }

    fun updateSelectedLength(length: Int) {
        this.length.value = length

        generateInitialValues()
        reset()
        saveToRepository()
    }

    fun updateShouldSaveSettings(shouldSaveSettings: Boolean) {
        this.shouldSaveSettings.value = shouldSaveSettings
        saveToRepository()
    }

    private fun onSwapElements() {

    }

    private fun pauseSorting() {
        sortingStatus.value = SortingStatus.PAUSED
    }

    private fun resumeSorting() {
        sortingStatus.value = SortingStatus.RUNNING
    }

    private suspend fun onCompareElements(comparingIndices: MutableList<Int>) {
        delay(delay.value.toMillis())

        while (sortingStatus.value != SortingStatus.RUNNING) {

        }

        this.comparingIndices.value = comparingIndices
    }

    fun reset() {
        sortingInteractor.reset()

        sortedData.value = initialData.value.toMutableList()
        comparingIndices.value = mutableListOf()
        sortingStatus.value = SortingStatus.INITIAL
    }

    fun onPressPlayButton() {
        when (sortingStatus.value) {
            SortingStatus.RUNNING -> pauseSorting()
            SortingStatus.PAUSED -> resumeSorting()
            SortingStatus.INITIAL -> startSorting()
        }
    }

    private fun startSorting() {
        comparingIndices.value = mutableListOf()
        sortingStatus.value = SortingStatus.RUNNING
        onSwapElements = ::onSwapElements
        onCompareElements = ::onCompareElements

        viewModelScope.launch {
            sortingInteractor.startSorting(
                algorithmType.value,
                sortedData.value,
                delay.value,
                onSwapElements,
                onCompareElements
            )
        }
    }


    private fun saveToRepository() {
        viewModelScope.launch {
            repository.saveAlgorithmType(algorithmType.value)
            repository.saveValueType(valueType.value)
            repository.saveLength(length.value)
            repository.saveDelay(delay.value)
            repository.saveShouldSaveSettings(shouldSaveSettings.value)
        }
    }
}
