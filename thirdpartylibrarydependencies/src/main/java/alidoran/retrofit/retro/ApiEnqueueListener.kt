package alidoran.retrofit.retro

interface ApiEnqueueListener {
    fun onBefore()
    fun <T> onSuccess(result: T)
    fun onFailure(errorMessage: String?)
    fun onAfter()
}