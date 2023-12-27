package com.example.sortingalgorithmsvisualization.application.api.external.repository

import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import com.example.sortingalgorithmsvisualization.application.ValueType
import java.time.Duration

interface SortingRepository {
    suspend fun getAlgorithmType(): AlgorithmType

    suspend fun saveAlgorithmType(algorithmType: AlgorithmType)

    suspend fun getValueType(): ValueType

    suspend fun saveValueType(valueType: ValueType)

    suspend fun getLength(): Int

    suspend fun saveLength(length: Int)

    suspend fun getDelay(): Duration

    suspend fun saveDelay(delay: Duration)

    suspend fun getShouldSaveSettings(): Boolean

    suspend fun saveShouldSaveSettings(shouldSaveSettings: Boolean)
}