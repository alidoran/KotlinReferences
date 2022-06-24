package alidoran.apis.graphql_retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object GraphQLInstance {
    private const val BASE_URL: String = "https://graphql-weather-api.herokuapp.com/"

    val graphQLService: GraphQLService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GraphQLService::class.java)
    }
}