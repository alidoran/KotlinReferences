package alidoran.third_party.rx_java

import alidoran.third_party.FakeRx3Api
import android.os.Bundle
import alidoran.third_party.databinding.ActivityRxJavaSimpleBinding
import android.app.Activity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@Suppress("unused")
class RxJavaSimpleActivity : Activity() {

    private lateinit var disposable: io.reactivex.disposables.Disposable

    private lateinit var binding: ActivityRxJavaSimpleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxJavaSimpleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }


    private fun initEvent() = with(binding) {
        btnJust.setOnClickListener {
            justLearn(RxJavaFullModel("Ali", 18))
        }
        btnSubscribe.setOnClickListener {
            subscribeRxLearn(provideRxObservableFullLearn())
        }
        btnMap.setOnClickListener { mapLearn() }
        btnFlatMap.setOnClickListener { flatMapLearn() }
        btnDoOnNext.setOnClickListener { doOnNextLearn() }
        btnComposeLearn.setOnClickListener { composLearn() }
        btnSingleTransform.setOnClickListener { observableTransformer().apply(provideRxObservableFullLearn())
        }
    }

    private fun <T> subscribeRxLearn(observable: Observable<T>) {
        disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                when (it) {
                    is RxJavaFullModel ->
                        Log.d("AliDoran", "${it.name} ${it.code}")

                    is RxJavaNameModel ->
                        Log.d("AliDoran", it.name)
                }
            }
    }

    private fun <T> justLearn(t: T): Observable<T> {
        return Observable.just(t)
    }


    private fun mapLearn() {
        val mObservables = provideRxObservableFullLearn()
        val mapObserve = mObservables.map {
            RxJavaNameModel(
                "${it.name} ${it.code}"
            )
        }
        subscribeRxLearn(mapObserve)
    }

    private fun flatMapLearn() {
        val mObservables = provideRxObservableFullLearn()
        val mapObserve = mObservables.flatMap {
            Observable.just(
                RxJavaNameModel(
                    "${it.name} ${it.code}"
                )
            )
        }
        subscribeRxLearn(mapObserve)
    }

    private fun doOnNextLearn() {
        val mObservables = provideRxObservableFullLearn()
        val observableDoOnNext = mObservables
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d("AliDoran", "doOnNext = ${it.name}")
                RxJavaNameModel( //Doesn't affect output
                    "${it.name} ${it.code}"
                )
            }
        subscribeRxLearn(observableDoOnNext)
    }

    private fun composLearn() {
        val mObservables = provideRxObservableFullLearn()
        val observableCompose = mObservables
            .compose {
                provideRxObservableNameLearn()
            }
        subscribeRxLearn(observableCompose)
    }

    private fun observableTransformer(): ObservableTransformer<RxJavaFullModel, RxJavaNameModel> {
        return ObservableTransformer<RxJavaFullModel, RxJavaNameModel> { upstream ->
            upstream.flatMap {
                Observable.just(
                    RxJavaNameModel(
                        "${it.name} ${it.code}"
                    )
                )
            }
        }
    }


    private fun <T> Observable<T>.delayEachLearn(
        interval: Long,
        timeUnit: TimeUnit
    ): Observable<T> =
        Observable.zip(
            this,
            Observable.interval(interval, timeUnit)
        ) { item, _ -> item }

    private fun provideRxObservableFullLearn(): Observable<RxJavaFullModel> {
        return Observable.just(
            RxJavaFullModel(
                "doran ali",
                18
            )
        ).delayEachLearn(5, TimeUnit.SECONDS)
    }

    private fun provideRxObservableNameLearn(): Observable<RxJavaNameModel> {
        return Observable.just(
            RxJavaNameModel(
                "doran ali"
            )
        ).delayEachLearn(5, TimeUnit.SECONDS)
    }

    // This is an example for using RxJava3 Methods
    // Don't care to the nested methods. Those only has been created for those
    @Suppress
    private fun uploadImageProcess() : Disposable =
        FakeRx3Api.fakeUploadImage(true)
            .doOnSubscribe{showLoading()}
            .flatMap { resizeImage() }
            .flatMapCompletable { uploadImage() }
            .subscribe(::hideLoading)


    private fun showLoading(){}
    private fun resizeImage() = FakeRx3Api.fakeStringFlowable()
    private fun uploadImage() = Completable.complete()
    private fun hideLoading(){}
}