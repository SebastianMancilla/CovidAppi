package com.example.covidapp.interfaces

import com.example.covidapp.models.News_DataItem
import retrofit2.Call
import retrofit2.http.GET

interface News_ApiInterface {
    @GET("news")
    fun getNewsData():Call<List<News_DataItem>>
}