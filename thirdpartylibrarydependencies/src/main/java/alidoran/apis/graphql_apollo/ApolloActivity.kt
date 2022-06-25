package alidoran.apis.graphql_apollo

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alidoran.thirdpartylibrarydependencies.LaunchListQuery
import com.alidoran.thirdpartylibrarydependencies.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ApolloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apollo)

        GlobalScope.launch {
            Log.d(TAG, "onCreate: ")
            val response = apolloClient.query(LaunchListQuery()).execute()
            Log.d(TAG,response.data!!.launches.launches.get(0)!!.site.toString())
        }


    }
}