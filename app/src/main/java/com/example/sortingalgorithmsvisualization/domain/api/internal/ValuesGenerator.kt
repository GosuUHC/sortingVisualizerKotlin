package com.example.sortingalgorithmsvisualization.domain.api.internal

interface ValuesGenerator {
    fun generate(length: Int): MutableList<Int>
}
