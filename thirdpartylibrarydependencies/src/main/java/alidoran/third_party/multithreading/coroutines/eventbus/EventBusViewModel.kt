package alidoran.third_party.multithreading.coroutines.eventbus

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EventBusViewModel : ViewModel() {

    private var charCount = 0
    private var booleanCount = 0
    private var intCount = 0

    init {
        Log.d("TAG", ": ")
    }

    fun getValuesCount(){
        Log.d("getValuesCount", "String: $charCount \n Boolean: $booleanCount \n Int:$intCount")
    }

    fun clearValuesCount(){
        charCount = 0
        booleanCount = 0
        intCount = 0
    }

    fun startComplexEventBusListener(){
        viewModelScope.launch {
            EventBus.on(this) {
                Log.d("EventBusActivity", "onCreate: $it")
            }
        }

        viewModelScope.launch {
            EventBusComplex.register<Char> {
                charCount++
            }
        }

        viewModelScope.launch {
            EventBusComplex.register<Int> {
                intCount++
            }
        }

        viewModelScope.launch {
            EventBusComplex.register<Boolean> {
                booleanCount++
            }
        }
    }
}