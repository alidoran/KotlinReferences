package alidoran.third_party.apis.rest.retrofit_center_run

abstract class ApiEnqueueListener {
    open fun onBefore(){}
    abstract fun <T> onSuccess(result: T)
    open fun onFailure(errorMessage: String?){}
    open fun onAfter(){}
}