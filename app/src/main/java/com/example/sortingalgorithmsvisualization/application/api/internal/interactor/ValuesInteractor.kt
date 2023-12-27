package com.example.sortingalgorithmsvisualization.application.api.internal.interactor

import com.example.sortingalgorithmsvisualization.application.ValueType

interface ValuesInteractor {
    fun getValues(valueType: ValueType, length: Int): MutableList<Int>
}