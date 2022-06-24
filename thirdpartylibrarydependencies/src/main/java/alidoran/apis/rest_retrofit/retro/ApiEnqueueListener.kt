package alidoran.apis.rest_retrofit.retro

abstract class ApiEnqueueListener {
    open fun onBefore(){}
    abstract fun <T> onSuccess(result: T)
    open fun onFailure(errorMessage: String?){}
    open fun onAfter(){}
}