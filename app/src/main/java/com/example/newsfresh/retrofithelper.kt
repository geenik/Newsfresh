package com.example.newsfresh

import com.google.gson.Gson
import com.squareup.okhttp.*
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.internal.http.RealResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okio.GzipSource
import okio.IOException
import okio.Okio
import okio.buffer
import com.google.gson.GsonBuilder
object retrofithelper {

    private const val BASE_URL="https://newsapi.org/v2/"
    fun getinstace():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}


