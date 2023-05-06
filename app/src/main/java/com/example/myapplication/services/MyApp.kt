package com.example.myapplication.services

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp: Application() {
    companion object{
        private lateinit var instance:MyApp
        fun getInstance() :MyApp = instance
    }

    private var retrofit: Retrofit? = null
    override fun onCreate() {
        super.onCreate()
        instance = this

        retrofit = Retrofit.Builder()
            .baseUrl("http://jolynjoestar12.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiServices(): ApiServices =retrofit!!.create(ApiServices::class.java)



}