package alidoran.android.data_store

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension Property for DataStore Initialization
private val Context.dataStore by preferencesDataStore(name = "settings")

class PreferencesHelper(private val context: Context) {

    companion object {
        private val SAMPLE_TEXT = stringPreferencesKey("sample_text")
    }

    suspend fun saveSampleText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[SAMPLE_TEXT] = text
        }
    }

    fun getSampleText(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[SAMPLE_TEXT] ?: ""
        }
    }
}
