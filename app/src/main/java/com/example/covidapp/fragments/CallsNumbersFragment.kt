package com.example.covidapp.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.adapters.CallsN_Adapter
import com.example.covidapp.interfaces.CallsN_ApiInterface
import com.example.covidapp.models.Calls_Numbers_Data
import kotlinx.android.synthetic.main.fragment_calls_numbers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CallsNumbersFragment : Fragment() {

    lateinit var  callsAdapter: CallsN_Adapter
    private lateinit var  linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls_numbers, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataCalls()//generate items for RecyclerView
        startRecyclerView()//start RecyclerView


    }

    /* getDataCalls()
    * this fun generate data of item for send to RecyclerView and displayed in */
    private fun getDataCalls() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://gtpreviene.researchmobile.co:3000/api/") //baseUrl
            .build()
            .create(CallsN_ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCallNData()

        retrofitData.enqueue(object : Callback<Calls_Numbers_Data?>{
            override fun onResponse(
                call: Call<Calls_Numbers_Data?>,
                response: Response<Calls_Numbers_Data?>
            ) {
                val responseBody = response.body()!!
                callsAdapter = CallsN_Adapter(requireActivity(),responseBody.data )
                rvCallsNumbers.adapter = callsAdapter

            }

            override fun onFailure(call: Call<Calls_Numbers_Data?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }

        })

    }


    private fun startRecyclerView() {
        rvCallsNumbers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireActivity())
        rvCallsNumbers.layoutManager = linearLayoutManager

    }




}