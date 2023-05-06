package com.example.myapplication.ui.dashboard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MunSeqItem(
    var id:Int,
    var CVE_CONCATENADA:Int,
    var CVE_ent:Int,
    var CVE_mun:Int,
    var NOMBRE_MUN: String,
    var ENTIDAD:String,
    var ORG_CUENCA:String,
    var ClV_OC:String,
    var CON_cuenca:String,
    var CVE_CONC:Int,
    var TIPOSDESEQUÍA: String,
    var CLAVE: String) : Parcelable {


}