package co.novu.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://api.novu.co/v1/"
    val apiKey = "7ce3f39fe50967f68ab36f775ed1e79f"

    fun getInstance(): Retrofit {

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