package alidoran.third_party.rx_java

import alidoran.third_party.databinding.ActivityRxJavaBinding
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch

class RxJavaActivity : AppCompatActivity() {

    private val retrofitRxJavaFragment = RetrofitRxJavaFragment()
    lateinit var binding: ActivityRxJavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxJavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initEvent()
    }

    private fun initEvent() = with(binding) {
        btnJustObservable.setOnClickListener {
            startRStream()
        }

        btnRetrofitRxJava.setOnClickListener {
            if (retrofitRxJavaFragment.isResumed)
                closeFragment()

            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, retrofitRxJavaFragment)
                .commit()
        }

        btnRxJavaSimpleActivity.setOnClickListener {
            startActivity(Intent(this@RxJavaActivity, RxJavaSimpleActivity::class.java))
        }
        btnRxJavaNext.setOnClickListener { callPriority() }
        btnRxJavaZip.setOnClickListener { zip() }
        btnCompletableBlocking.setOnClickListener { completableBlockingAwait() }
    }

    private fun closeFragment() {
        supportFragmentManager
            .beginTransaction()
            .remove(binding.fragmentContainer.getFragment()).commit()
    }


    private fun startRStream() {
//Create an Observable//
        val myObservable = getJustObservable()

//Create an Observer//
        val myObserver = getObserver()

//Subscribe myObserver to myObservable//
        myObservable
            .subscribe(myObserver)


    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(s: String) {
                println("onNext: $s")
            }

            override fun onError(e: Throwable) {
                println("onError: " + e.message)
            }

            override fun onComplete() {
                println("onComplete")
            }
        }
    }

//Give myObservable some data to emit//

    private fun getJustObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }

    private fun getJustObservableList(): Observable<List<String>> {
        return Observable.just(listOf("1", "2", "3", "4", "5"))
    }

    private fun getJustObservableSecondList(): Observable<List<String>> {
        return Observable.just(listOf("a", "b", "c", "d", "e"))
    }

    private fun callPriority() {
        lifecycleScope.launch {
            FakeRxEndPoint.fakeRxJavaCall()
                .doAfterNext { Log.d("callPriority", "doAfterNext") }
                .doOnNext { Log.d("callPriority", "doOnNext ") }
                .flatMap {
                    Log.d("callPriority", "flatMap ")
                    Observable.just(it)
                }
                .subscribe { Log.d("callPriority", "subscribe ") }
        }
    }

    data class Dt(
        val mInt: Int?,
        val mString: String?
    )

    private fun zip() {
        val list = listOf<String>()
        val zipResult = Observable.zip(
            getJustObservableList(), getJustObservableSecondList()
        ) { mString: List<String>, mString2: List<String> ->
            list.plus(mString).plus(mString2)
        }

        lifecycleScope.launch {
            zipResult.subscribe {
                Log.d("zip", "zip: ${it[0]}")
            }
        }
    }

    private fun completableBlockingAwait() {
        lifecycleScope.launch {
            FakeRxEndPoint.fakeRxJavaCall().flatMapCompletable {
                Log.d("TAG", "completableBlockingAwait: 1")
                FakeRxEndPoint.fakeRxJavaCompletableCall(true)
            }.blockingAwait()
            Log.d("TAG", "completableBlockingAwait: 2")
        }
        Log.d("TAG", "completableBlockingAwait: 3")
    }
}