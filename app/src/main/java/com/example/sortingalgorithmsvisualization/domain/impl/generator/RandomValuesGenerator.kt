package com.example.sortingalgorithmsvisualization.domain.impl.generator

import com.example.sortingalgorithmsvisualization.domain.api.internal.ValuesGenerator
import kotlin.random.Random

class RandomValuesGenerator : ValuesGenerator {
    override fun generate(length: Int): MutableList<Int> {
        val random = Random.Default
        return MutableList<Int>(length) { random.nextInt(length) }
    }
}