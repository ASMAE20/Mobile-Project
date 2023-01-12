package com.example.nextprojet.folder_api


import com.example.nextprojet.tools.Global.Companion.web_site
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// initialisation de retrofit
object Retrofit {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(web_site)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val api: PosteService by lazy {
        retrofit.create(PosteService::class.java)
    }
}