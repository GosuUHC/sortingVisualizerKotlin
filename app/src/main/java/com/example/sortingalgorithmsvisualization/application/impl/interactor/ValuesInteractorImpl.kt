package com.example.sortingalgorithmsvisualization.application.impl.interactor

import com.example.sortingalgorithmsvisualization.application.ValueType
import com.example.sortingalgorithmsvisualization.application.api.internal.interactor.ValuesInteractor
import com.example.sortingalgorithmsvisualization.domain.api.factory.ValuesGeneratorsFactory
import com.example.sortingalgorithmsvisualization.domain.api.internal.ValuesGenerator

class ValuesInteractorImpl : ValuesInteractor {
    override fun getValues(valueType: ValueType, length: Int): MutableList<Int> {
        val generator = configureGenerator(valueType)
        return generator.generate(length)
    }

    private fun configureGenerator(valueType: ValueType): ValuesGenerator {
        return when (valueType) {
            ValueType.RANDOM -> ValuesGeneratorsFactory.createRandomGenerator()
            ValueType.REVERSED -> ValuesGeneratorsFactory.createReversedGenerator()
        }
    }
}