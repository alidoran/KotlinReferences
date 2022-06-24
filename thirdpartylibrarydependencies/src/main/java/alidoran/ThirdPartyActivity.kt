package alidoran

import alidoran.apis.graphql_retrofit.GraphQLActivity
import alidoran.coroutines.CoroutineActivity
import alidoran.di.DiActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alidoran.thirdpartylibrarydependencies.databinding.ActivityThirdpartyBinding
import com.example.retrofitteach.models.Current
import com.example.retrofitteach.models.Location
import alidoran.apis.rest_retrofit.retro.WeatherApi
import alidoran.apis.rest_retrofit.retro.WeatherService

class ThirdPartyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThirdpartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDi.setOnClickListener {
            val intent = Intent(this, DiActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoroutine.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }

        binding.btnRetrofit.setOnClickListener {
            WeatherApi.instance.weatherNow("Tehran", object : WeatherService {
                override fun location(location: Location) {
                    val txt = "Location = $location.name || localTime = ${location.localtime}"
                    Toast.makeText(baseContext, txt, Toast.LENGTH_LONG).show()
                }

                override fun current(current: Current) {

                }
            })
        }

        binding.btnGraphql.setOnClickListener {
            val intent = Intent(this, GraphQLActivity::class.java)
            startActivity(intent)
        }

    }
}