package mx.com.pokemon.project.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private lateinit var retrofit: Retrofit

    fun provideRetrofit(): ApiService {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .readTimeout(110L, TimeUnit.SECONDS)
            .writeTimeout(110L, TimeUnit.SECONDS)
            .followRedirects(false)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(UrlBaseService.getUrlBase())
            .client(clientBuilder)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}