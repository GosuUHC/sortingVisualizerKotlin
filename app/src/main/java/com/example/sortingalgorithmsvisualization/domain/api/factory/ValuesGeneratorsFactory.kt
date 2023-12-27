package com.example.sortingalgorithmsvisualization.domain.api.factory

import com.example.sortingalgorithmsvisualization.domain.api.internal.ValuesGenerator
import com.example.sortingalgorithmsvisualization.domain.impl.generator.RandomValuesGenerator
import com.example.sortingalgorithmsvisualization.domain.impl.generator.ReversedValuesGenerator

object ValuesGeneratorsFactory {
//    fun createFewUniqueValuesGenerator(): ValuesGenerator =
//        FewUniqueValuesGenerator()
//
//    fun createNearlySortedGenerator(): ValuesGenerator =
//        NearlySortedValuesGenerator()
//
//    fun createOddEvenSplitGenerator(): ValuesGenerator =
//        OddEvenSplitValuesGenerator()

    fun createRandomGenerator(): ValuesGenerator = RandomValuesGenerator()

    fun createReversedGenerator(): ValuesGenerator = ReversedValuesGenerator()
}