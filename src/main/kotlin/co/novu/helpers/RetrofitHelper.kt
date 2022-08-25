package co.novu.helpers

import mu.KotlinLogging
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val logger = KotlinLogging.logger {}



class RetrofitHelper(
    private val baseUrl: String,
    private val apiKey: String
) {

    fun getInstance(): Retrofit {

        logger.info { "back url $baseUrl" }
        val httpClient = OkHttpClient.Builder()

        httpClient
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "ApiKey $apiKey")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()


        return Retrofit.Builder().client(httpClient.build()).baseUrl(baseUrl)

            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}