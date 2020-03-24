package com.angel.stocksearch.rest.client

import android.content.Context
import com.angel.stocksearch.BuildConfig
import com.angel.stocksearch.rest.service.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val sInstance: HashMap<String, Retrofit> = HashMap(3)

    fun getApiService(): ApiService {
        val baseUrl = "https://api.myjson.com"
        return getApiService(baseUrl)
    }

    fun getApiService(baseURL: String): ApiService {
        var retrofit: Retrofit?
        if (sInstance.containsKey(baseURL)) {
            retrofit = sInstance[baseURL]
        } else {
            val gson: Gson = GsonBuilder().setLenient().create();

            var builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                // set your desired log level
                logging.level = HttpLoggingInterceptor.Level.BODY
                builder = builder.addInterceptor(logging)
            }
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(builder.build())
                .build()
            sInstance[baseURL] = retrofit
        }
        return retrofit!!.create(ApiService::class.java)
    }
}