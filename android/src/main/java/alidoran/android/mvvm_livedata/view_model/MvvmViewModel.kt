package alidoran.android.mvvm_livedata.view_model


import alidoran.android.mvvm_livedata.model.MvvmModel
import alidoran.android.mvvm_livedata.model.MvvmModelDataProvider
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class MvvmViewModel : ViewModel() {
    private val mvvmData = MutableLiveData<MvvmModel>()
    private val mvvmModelList = MvvmModelDataProvider().getMvvmModelList()
    private var currentIndex = 0
    private val delayTime = TimeUnit.SECONDS.toMillis(2)

    init {
        loop()
    }

    private fun updateMvvmModels(): MvvmModel {
        currentIndex++
        currentIndex %= mvvmModelList.size
        return mvvmModelList[currentIndex]
    }


    private fun loop() {
        Handler(Looper.getMainLooper()).post {
            TimeUnit.SECONDS.sleep(2)
            updateMvvmModels()
        }
    }

    fun getLiveMvvmData(): LiveData<MvvmModel> = liveData {
        while (true) {
            emit(updateMvvmModels())
            delay(delayTime)
        }
    }
}