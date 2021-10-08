package com.example.covidapp.interfaces

import com.example.covidapp.models.Maps_DataItem
import retrofit2.Call
import retrofit2.http.GET

interface Maps_ApiInterface {
    @GET("centres")
    fun getCentresMarkers(): Call<List<Maps_DataItem>>
}