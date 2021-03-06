package alidoran.third_party

import alidoran.third_party.apis.graphql_apollo.ApolloActivity
import alidoran.third_party.apis.graphql_retrofit.GraphQLActivity
import alidoran.third_party.coroutines.CoroutineActivity
import alidoran.third_party.di.DiActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import alidoran.third_party.apis.rest.models.Current
import alidoran.third_party.apis.rest.models.Location
import alidoran.third_party.apis.rest.retro_dagger.CallWeatherDagger
import alidoran.third_party.apis.rest.retro_standard.CallWeatherApi2
import alidoran.third_party.apis.rest.retrofit_center_run.WeatherApi
import alidoran.third_party.apis.rest.retrofit_center_run.WeatherService
import alidoran.third_party.apis.rest.retrofit_java.RestRetroJavaActivity
import alidoran.third_party.databinding.ActivityThirdpartyBinding

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

        binding.btnApollo.setOnClickListener {
            val intent = Intent(this, ApolloActivity::class.java)
            startActivity(intent)
        }

        binding.btnRestRetroJava.setOnClickListener {
            val intent = Intent(this, RestRetroJavaActivity::class.java)
            startActivity(intent)
        }

        binding.btnRestRetroStandard.setOnClickListener {
            CallWeatherApi2().weatherNow()
        }

        binding.btnRestRetroDagger.setOnClickListener {
            CallWeatherDagger()
        }
    }
}