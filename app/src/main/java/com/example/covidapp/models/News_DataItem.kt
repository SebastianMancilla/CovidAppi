package com.example.covidapp.models

data class News_DataItem(
    val active: Int,
    val category_id: Int,
    val detail: String,
    val end: String,
    val id: Int,
    val image: String,
    val name: String,
    val start: String,
    val title: String
)