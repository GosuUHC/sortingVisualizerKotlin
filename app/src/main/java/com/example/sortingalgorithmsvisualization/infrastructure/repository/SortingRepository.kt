package com.example.sortingalgorithmsvisualization.infrastructure.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.sortingalgorithmsvisualization.application.AlgorithmType
import com.example.sortingalgorithmsvisualization.application.ValueType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration

class SortingRepository(private val preferences: SharedPreferences) {

    suspend fun getAlgorithmType(): AlgorithmType = withContext(Dispatchers.IO) {
        val algorithmName = preferences.getString("algorithm_type", "BUBBLE")
        return@withContext AlgorithmType.valueOf(algorithmName!!)
    }

    suspend fun saveAlgorithmType(algorithmType: AlgorithmType) = withContext(Dispatchers.IO) {
        try {
            preferences.edit().apply {
                putString("algorithm_type", algorithmType.name)
                apply()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }

    suspend fun getValueType(): ValueType = withContext(Dispatchers.IO) {
        val valueTypeName = preferences.getString("value_type", "RANDOM")
        if (valueTypeName != null) {
            return@withContext ValueType.valueOf(valueTypeName)
        }

        return@withContext ValueType.RANDOM
    }

    suspend fun saveValueType(valueType: ValueType) = withContext(Dispatchers.IO) {
        try {
            preferences.edit().apply {
                putString("value_type", valueType.name)
                apply()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }

    suspend fun getLength(): Int = withContext(Dispatchers.IO) {
        try {
            return@withContext preferences.getInt("length", 50)
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }

    suspend fun saveLength(length: Int) = withContext(Dispatchers.IO) {
        try {
            preferences.edit().apply {
                putInt("length", length)
                apply()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }

    suspend fun getDelay(): Duration = withContext(Dispatchers.IO) {
        try {
            return@withContext Duration.ofMillis(preferences.getLong("delay", 30L))
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }

        return@withContext Duration.ofMillis(30)
    }

    suspend fun saveDelay(delay: Duration) = withContext(Dispatchers.IO) {
        try {
            preferences.edit().apply {
                putLong("delay", delay.toMillis())
                apply()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }

    suspend fun getShouldSaveSettings(): Boolean = withContext(Dispatchers.IO) {
        return@withContext preferences.getBoolean("should_save_settings", false)
    }

    suspend fun saveShouldSaveSettings(shouldSaveSettings: Boolean) = withContext(Dispatchers.IO) {
        try {
            preferences.edit().apply {
                putBoolean("should_save_settings", shouldSaveSettings)
                apply()
            }
        } catch (e: Exception) {
            Log.e("Repository", "Error: $e")
        }
    }
}
