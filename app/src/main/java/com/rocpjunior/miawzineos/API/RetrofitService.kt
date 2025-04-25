package com.rocpjunior.miawzineos.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.jvm.java

class RetrofitService {

    companion object{
        const val URL_BASE = "https://api.imgur.com/3/"

        val retrofit  = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor())
                    .build()
            )
            .build()
        val miawAPI = retrofit.create(MiawAPI::class.java)
    }
}