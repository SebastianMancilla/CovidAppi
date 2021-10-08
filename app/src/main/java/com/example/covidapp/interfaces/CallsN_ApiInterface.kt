package com.example.covidapp.interfaces

import com.example.covidapp.models.Calls_Numbers_Data
import retrofit2.Call
import retrofit2.http.GET

interface CallsN_ApiInterface {
    @GET("information")
    fun getCallNData():Call<Calls_Numbers_Data>

}