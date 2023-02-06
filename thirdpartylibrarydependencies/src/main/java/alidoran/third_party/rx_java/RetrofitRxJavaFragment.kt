package alidoran.third_party.rx_java

import alidoran.third_party.apis.rest.retro_standard.getWeatherServiceRx
import alidoran.third_party.databinding.FragmentRetrofitRxJavaBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitRxJavaFragment : Fragment() {

    lateinit var binding: FragmentRetrofitRxJavaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitRxJavaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCallRetrofitRxJava.setOnClickListener {
            val a= getWeatherServiceRx().getWeatherRx(q = "Tehran")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
                .subscribe {
                    Toast.makeText(context, it.location.name, Toast.LENGTH_LONG).show()
                }
        }
    }
}