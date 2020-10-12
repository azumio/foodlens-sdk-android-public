package com.azumio.android.foodlenslibrary.api

import android.util.Log
import com.azumio.android.foodlenslibrary.core.AccessTokenType
import com.azumio.android.foodlenslibrary.core.ArgusToken
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    private val okHttpClient by lazy { OkHttpClient() }
    private const val BASE_URL = "https://api.foodlens.com/"
    private var token:ArgusToken? = null
    private val retrofit: Retrofit by lazy {
        Log.e("AppClient", "Creating Retrofit Client")
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = okHttpClient.newBuilder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val original = chain.request()

                    token?.let {
                        val request = original.newBuilder()
                            .header("Authorization", it.httpAuthorizationHeaderRepresentationForType())
                            .method(original.method, original.body)
                            .build()
                        return chain.proceed(request)
                    }
                    return chain.proceed(original)

                }
            })
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .dispatcher(dispatcher)
            .build()
        builder.client(client).build()
    }



    fun <T> createService(tClass: Class<T>?): T {
        return retrofit.create(tClass)
    }
    fun <T> createService(tClass: Class<T>?,token:ArgusToken): T {
        this.token = token
        return retrofit.create(tClass)
    }
}