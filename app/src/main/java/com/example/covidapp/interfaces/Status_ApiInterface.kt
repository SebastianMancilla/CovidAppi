package com.example.covidapp.interfaces

import com.example.covidapp.models.Status_DataItem
import retrofit2.Call
import retrofit2.http.GET

interface Status_ApiInterface {
    @GET("covid")
    fun getStatusData(): Call<Status_DataItem>
}