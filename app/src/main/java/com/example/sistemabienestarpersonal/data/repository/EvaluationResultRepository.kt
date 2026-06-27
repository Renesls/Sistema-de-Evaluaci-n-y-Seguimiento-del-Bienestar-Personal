package com.example.sistemabienestarpersonal.data.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesKeys
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.sistemabienestarpersonal.model.EvaluationResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "evaluation_results"
private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class EvaluationResultRepository(private val context: Context) {
    private val gson = Gson()
    private val RESULTS_KEY = PreferencesKeys.stringSet("RESULTS_SET")

    /** Retrieve the persisted list of results */
    suspend fun getAll(): List<EvaluationResult> {
        val prefs = context.dataStore.data.first()
        val jsonSet = prefs[RESULTS_KEY] ?: emptySet()
        // We store each result as a JSON string in the set
        return jsonSet.map { gson.fromJson(it, EvaluationResult::class.java) }
    }

    /** Add a new result and persist the whole list */
    suspend fun addResult(result: EvaluationResult) {
        val current = getAll().toMutableList()
        current.add(0, result) // most recent first
        saveList(current)
    }

    /** Clear all stored results */
    suspend fun clearAll() {
        context.dataStore.edit { it[RESULTS_KEY] = emptySet() }
    }

    private suspend fun saveList(list: List<EvaluationResult>) {
        // Convert each result to JSON string, store in a Set (order not guaranteed, but we keep most recent first on retrieval)
        val jsonSet = list.map { gson.toJson(it) }.toSet()
        context.dataStore.edit { it[RESULTS_KEY] = jsonSet }
    }
}
