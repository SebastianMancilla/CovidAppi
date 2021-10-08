package com.example.covidapp.models

import com.google.gson.annotations.SerializedName

data class Maps_DataItem(
    @SerializedName("active") val active: Int,
    @SerializedName("description")val description: String,
    @SerializedName("id")val id: Int,
    @SerializedName("latitude")val latitude: String,
    @SerializedName("longitude")val longitude: String,
    @SerializedName("name")val name: String


){
    @JvmName("getLatitude1")
    fun getLatitude():String{
        return latitude
    }
}