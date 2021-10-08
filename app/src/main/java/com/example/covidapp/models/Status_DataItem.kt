package com.example.covidapp.models

data class Status_DataItem(
    val confirmed: Int,
    val country: String,
    val deaths: Int,
    val enable: Int,
    val lastUpdate: String,
    val recovered: Int
)