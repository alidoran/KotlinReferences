package alidoran.third_party.apis.graphql_apollo

import alidoran.third_party.databinding.ActivityApolloBinding
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rocketreserver.LaunchListQuery
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import weather.CityDetailsQuery

class ApolloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityApolloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApolloSample.setOnClickListener {
            GlobalScope.launch {
                Log.d(TAG, "onCreate: ")
                val response = apolloExampleClient.query(LaunchListQuery()).execute()
                Log.d(TAG,response.data!!.launches.launches.get(0)!!.site.toString())
            }
        }

        binding.btnWeather.setOnClickListener {
            GlobalScope.launch {
                Log.d(TAG, "onCreate: ")
                val response = weatherClient.query(CityDetailsQuery("Tehran")).execute()
                Log.d(TAG,response.toString())
            }
        }
    }
}