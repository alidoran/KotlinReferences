package alidoran.android.mvvm_livedata.view_model


import alidoran.android.mvvm_livedata.model.MvvmModel
import alidoran.android.mvvm_livedata.model.MvvmModelDataProvider
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MvvmViewModel : ViewModel() {
    private val _mvvmData = MutableLiveData<MvvmModel>()
    private val mvvmModelList = MvvmModelDataProvider().getMvvmModelList()
    private var currentIndex = 0
    private val delayTime = TimeUnit.SECONDS.toMillis(2)
    val mvvmData
        get() = _mvvmData

    init {
        loop()
    }

    private fun mvvmModelFactory(): MvvmModel {
        currentIndex++
        currentIndex %= mvvmModelList.size
        return mvvmModelList[currentIndex]
    }


    private fun loop() {
        viewModelScope.launch {
            while (true) {
                delay(delayTime)
                _mvvmData.value = mvvmModelFactory()
            }
        }
    }
}