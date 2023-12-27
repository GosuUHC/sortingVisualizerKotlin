package com.example.sortingalgorithmsvisualization.domain.impl.generator

import com.example.sortingalgorithmsvisualization.domain.api.internal.ValuesGenerator

class ReversedValuesGenerator : ValuesGenerator {
    override fun generate(length: Int): MutableList<Int> {
        return MutableList<Int>(length) { length - it }
    }
}