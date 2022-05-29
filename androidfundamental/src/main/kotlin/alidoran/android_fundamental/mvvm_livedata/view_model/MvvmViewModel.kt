package alidoran.android_fundamental.mvvm_livedata.view_model

import alidoran.android_fundamental.mvvm_livedata.model.MvvmModel
import alidoran.android_fundamental.mvvm_livedata.model.MvvmModelDataProvider
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

class MvvmViewModel : ViewModel() {
    private val mvvmData = MutableLiveData<MvvmModel>()
    private val mvvmModelList = MvvmModelDataProvider().getMvvmModelList()
    private var currentIndex = 0
    private val delay = TimeUnit.SECONDS.toMillis(2)

    init {
        loop()
    }

    private fun updateMvvmModels() {
        currentIndex++
        currentIndex %= mvvmModelList.size
        println("Before")
        mvvmData.value = mvvmModelList[currentIndex]
        println("After")
        loop()
    }


    private fun loop() {
        Handler(Looper.getMainLooper()).post {
            TimeUnit.SECONDS.sleep(2)
            updateMvvmModels()
        }
    }

    fun getLiveMvvmData(): LiveData<MvvmModel> = mvvmData
}