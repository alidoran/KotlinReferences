package alidoran.third_party.rx_java

import alidoran.third_party.databinding.ActivityRxJavaBinding
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxJavaActivity : AppCompatActivity() {

    private val retrofitRxJavaFragment = RetrofitRxJavaFragment()
    lateinit var binding: ActivityRxJavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxJavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJustObservable.setOnClickListener {
            startRStream()
        }

        binding.btnRetrofitRxJava.setOnClickListener {
            if (retrofitRxJavaFragment.isResumed)
                closeFragment()

            supportFragmentManager
                .beginTransaction()
                .add(binding.fragmentContainer.id, retrofitRxJavaFragment)
                .commit()
        }

        binding.btnRxJavaSimpleActivity.setOnClickListener {
            startActivity(Intent(this, RxJavaSimpleActivity::class.java))
        }
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

}