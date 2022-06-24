package alidoran.apis.rest_retrofit.retro

import com.example.retrofitteach.models.ErrorModel
import com.example.retrofitteach.tools.mapperModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

open class BaseApi private constructor() {

    companion object {
        val instance = BaseApi()
    }

    fun createBuilder(url: String): Retrofit? {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun <T> enqueue(runner: Call<T>, apiEnqueueListener: ApiEnqueueListener) {
        apiEnqueueListener.onBefore()
        runner.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                var result: T? = null
                var resultError: String? = null

                try {
                    result = response.body()
                    if (response.errorBody() != null && response.errorBody().toString()
                            .isNotEmpty()
                    ) {
                        val errorModel: ErrorModel = mapperModel(
                            response.errorBody()!!.string(),
                            ErrorModel::class.java
                        ) as ErrorModel
                        resultError = errorModel.Message
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                when {
                    response.code() == 200 -> {
                        apiEnqueueListener.onSuccess(result)
//                        apiEnqueueListener.onAfter()
                    }
                    resultError != null -> apiEnqueueListener.onFailure(
                        handlerFailure(
                            resultError.replace("\"", "")
                        )
                    )
                    else -> {
                        apiEnqueueListener.onFailure(null)
                        apiEnqueueListener.onAfter()
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiEnqueueListener.onFailure(handlerFailure(t.toString()))
                apiEnqueueListener.onAfter()
            }
        })
    }

//    fun <T> execute(runner: Call<T>): T? {
//        try {
//            val policy = ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//            val result = runner.execute()
//            if (result.isSuccessful) return result.body()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        return null
//    }

    private fun handlerFailure(errorMessage: String): String {
        return errorMessage
    }

}