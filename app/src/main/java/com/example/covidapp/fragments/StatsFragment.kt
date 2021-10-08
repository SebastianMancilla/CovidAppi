package com.example.covidapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.covidapp.R
import com.example.covidapp.adapters.Status_Adapter
import com.example.covidapp.interfaces.Status_ApiInterface
import com.example.covidapp.models.Status_DataItem
import kotlinx.android.synthetic.main.fragment_stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.anychart.palettes.RangeColors





class StatsFragment : Fragment() {
    lateinit var  statusAdapter: Status_Adapter
    lateinit var  linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataStatus()// get data for recycler
        startRecyclerView()// start recyclerView



    }

    /*getDataStatus
    * get data and send item in recycler, and  pieGraph*/
    private fun getDataStatus() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://gtpreviene.researchmobile.co:3000/api/")
            .build()
            .create(Status_ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getStatusData()

        retrofitData.enqueue(object : Callback<Status_DataItem?>{
            override fun onResponse(
                call: Call<Status_DataItem?>,
                response: Response<Status_DataItem?>
            ) {
                val responseBody = response.body()!!
                val list:ArrayList<Status_DataItem> = ArrayList()
                list.add(responseBody)
                statusAdapter = Status_Adapter(requireActivity(), list)
                rvStatus.adapter = statusAdapter
                initGraphPie(responseBody)



            }

            override fun onFailure(call: Call<Status_DataItem?>, t: Throwable) {
                Log.d("StatsFragment", "onFailure: " + t.message)
            }

        })
    }


    /*initGraphPie()
    * initialized pie graph in window sending the data of confirmed and recovered of api*/
    private fun initGraphPie(responseBody: Status_DataItem) {
        val  pie:Pie = AnyChart.pie()
        val dataEntry:ArrayList<DataEntry> = ArrayList()
        dataEntry.add(ValueDataEntry("Confirmados", responseBody.confirmed))// add item of pieGraph
        dataEntry.add(ValueDataEntry("Recuperados", responseBody.recovered))

        pie.data(dataEntry)//send data
        pie.title("Estadisticas COVID-19")
        val palette = RangeColors.instantiate() //signed palette colors of graph
        palette.items("#ff7052", "#f3a848")
        pie.palette(palette)


        val anyChartView:AnyChartView = pieChart

        anyChartView.setChart(pie)//initialized graph




    }

    private fun startRecyclerView(){
        rvStatus.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireActivity())
        rvStatus.layoutManager = linearLayoutManager
    }

}