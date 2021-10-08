package com.example.covidapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.adapters.News_Adapter

import com.example.covidapp.R
import com.example.covidapp.interfaces.News_ApiInterface
import com.example.covidapp.models.News_DataItem
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsFragment : Fragment() {
    lateinit var newsAdapter: News_Adapter
    private lateinit var  linearLayoutManager:LinearLayoutManager


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMyData()//get Data News
        startRecyclerView()//RecyclerView
    }


    /*
    * getMyData()
    * bring the corresponding data to load it into the item and then send it to the RecyclerView
    * */
    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://gtpreviene.researchmobile.co:3000/api/")//baseUrl
            .build()
            .create(News_ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getNewsData()


        retrofitData.enqueue(object : Callback<List<News_DataItem>?> {
            override fun onResponse(
                call: Call<List<News_DataItem>?>,
                response: Response<List<News_DataItem>?>
            ) {
                val responseBody = response.body()!!
                newsAdapter = News_Adapter(requireActivity(), responseBody)
                rvNews.adapter = newsAdapter
            }

            override fun onFailure(call: Call<List<News_DataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }

        })
    }


    /*
    * startRecyclerView
    * start RecyclerView in window
    * */
    private fun startRecyclerView(){
        //start RecyclerView
        rvNews.setHasFixedSize(true)
        linearLayoutManager =  LinearLayoutManager(requireActivity())
        rvNews.layoutManager = linearLayoutManager
    }




}