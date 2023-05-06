package com.example.myapplication.ui.notifications

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyForecastItem(
    var id: Int,
    var cc:Double,
    var desciel:String,
    var dh:Int,
    var dirvienc:String,
    var dirvieng:Double,
    var dloc:String,
    var ides:Int,
    var idmun:Int,
    var lat:Double,
    var lon: Double,

    var ndia:Int,
    var nes:String,
    var nmun:String,
    var prec:Double,
    var probprec:Int,
    var raf:Double,
    var tmax: Double,
    var tmin: Double,
    var velvien: Double
    ) : Parcelable {
}