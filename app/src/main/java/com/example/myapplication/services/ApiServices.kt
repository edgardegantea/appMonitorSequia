package com.example.myapplication.services

import com.example.myapplication.ui.dashboard.MunSeqItem
import com.example.myapplication.ui.home.tipoSequiaItem
import com.example.myapplication.ui.notifications.DailyForecastItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("TipSequia")
    fun clima() : Call<List<tipoSequiaItem>>

    @GET("TipSequiaElement/")
    fun getElementSequia(@Query("id") id: Int): Call<tipoSequiaItem>

    @GET("tseqElementAPIVIEW/")
    fun tseqelemt(@Query("search") n_mun:String) : Call<List<tipoSequiaItem>>


    @GET("MunSeq")
    fun Munseq() : Call<List<MunSeqItem>>

    @GET("MunSeqElement/")
    fun Munseqelemt(@Query("search") NOMBRE_MUN:String) : Call<List<MunSeqItem>>

    @GET("getMunSeqElement/")
    fun getElementSequiaDetails(@Query("CVE_CONCATENADA") CVE_concatenada: Int): Call<MunSeqItem>


    @GET("DailyForecast")
    fun DailyForecast() : Call<List<DailyForecastItem>>

    @GET("DailyForecastElement/")
    fun DailyForecastElement(@Query("search") NOMBRE_MUN:String) : Call<List<DailyForecastItem>>

    @GET("GETDailyForecastElementAPIVIEW/")
    fun GETDailyForecastElementAPIVIEW(@Query("id") id: Int): Call<DailyForecastItem>


}