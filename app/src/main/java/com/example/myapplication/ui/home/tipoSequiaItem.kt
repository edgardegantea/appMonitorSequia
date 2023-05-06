package com.example.myapplication.ui.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class tipoSequiaItem(
    var id:Int,
    var clave:Int,
    var entidad:String,
    var D0:Int,
    var D1:Int,
    var D2:Int,
    var D3:Int,
    var D4:Int,
    var MunicipiosSequia:Int,
    var porcentajeSequia: Double) : Parcelable {


}
