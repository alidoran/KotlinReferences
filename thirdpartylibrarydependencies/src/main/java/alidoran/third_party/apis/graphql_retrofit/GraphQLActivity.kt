package alidoran.third_party.apis.graphql_retrofit

import alidoran.third_party.databinding.ActivityGraphqlBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class GraphQLActivity : AppCompatActivity() {

    lateinit var binding: ActivityGraphqlBinding
    var cityModel: CityModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphqlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        post("Madrid")
    }

    private fun post(city: String) {
        val retrofit = GraphQLInstance.graphQLService
        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "query {getCityByName(name: \"$city\") {id,name,country,coord {lon,lat}}}"
        )

        runBlocking {
            try {
                val response = retrofit.postDynamicQuery(paramObject.toString())
                if (response.isSuccessful) {
                    Log.e("response", response.body().toString())
                    cityModel = response.body()
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}
