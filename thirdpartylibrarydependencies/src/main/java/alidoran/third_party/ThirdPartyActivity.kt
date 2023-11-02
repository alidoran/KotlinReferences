package alidoran.third_party

import alidoran.third_party.apis.graphql_apollo.ApolloActivity
import alidoran.third_party.apis.graphql_retrofit.GraphQLActivity
import alidoran.third_party.apis.rest.retro_dagger.CallWeatherDagger
import alidoran.third_party.apis.rest.retro_standard.CallWeatherApi2
import alidoran.third_party.apis.rest.retro_standard.RetrofitActivity
import alidoran.third_party.apis.rest.retrofit_java.RestRetroJavaActivity
import alidoran.third_party.app_status.AppStatusHelp
import alidoran.third_party.multithreading.coroutines.CoroutineActivity
import alidoran.third_party.camera_x.CameraXActivity
import alidoran.third_party.courier.CourierSampleActivity
import alidoran.third_party.databinding.ActivityThirdpartyBinding
import alidoran.third_party.di.DiActivity
import alidoran.third_party.firebase.FirebaseActivity
import alidoran.third_party.google_map.GoogleMapActivity
import alidoran.third_party.multithreading.MultiThread
import alidoran.third_party.rx_java.RxJavaActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThirdPartyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThirdpartyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppStatusHelp(this).getIsAppInBackground()

        binding.btnDi.setOnClickListener {
            val intent = Intent(this, DiActivity::class.java)
            startActivity(intent)
        }

        binding.btnCoroutine.setOnClickListener {
            val intent = Intent(this, CoroutineActivity::class.java)
            startActivity(intent)
        }

        binding.btnRetrofit.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
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

        binding.btnRxJava.setOnClickListener {
            val intent = Intent(this, RxJavaActivity::class.java)
            startActivity(intent)
        }

        binding.btnFirebase.setOnClickListener {
            val intent = Intent(this, FirebaseActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoogleMap.setOnClickListener {
            val intent = Intent(this, GoogleMapActivity::class.java)
            startActivity(intent)
        }

        binding.btnCameraX.setOnClickListener {
            val intent = Intent(this, CameraXActivity::class.java)
            startActivity(intent)
        }

        binding.btnCameraX.setOnClickListener {
            val intent = Intent(this, CameraXActivity::class.java)
            startActivity(intent)
        }

        binding.btnMultiThread.setOnClickListener{
            val intent = Intent(this, MultiThread::class.java)
            startActivity(intent)
        }

        binding.btnCourier.setOnClickListener{
            val intent = Intent(this, CourierSampleActivity::class.java)
            startActivity(intent)
        }
    }
}