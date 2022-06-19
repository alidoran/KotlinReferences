package android.mvvm_livedata.model

class MvvmModelDataProvider {

    private val mvvmModelList = arrayListOf<MvvmModel>()

    init {
        mvvmModelList.add(MvvmModel(2, "Ali"))
        mvvmModelList.add(MvvmModel(1, "Roya"))
    }

    fun getMvvmModelList() = mvvmModelList
}