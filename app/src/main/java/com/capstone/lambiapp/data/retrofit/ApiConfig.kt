package com.capstone.lambiapp.data.retrofit

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.michaelevans.colorart.library.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
        fun getApiService (context: Context): ApiService {

            val loginInterceptor = if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .addInterceptor(loginInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://backend-service-1-dot-fashap.et.r.appspot.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
}