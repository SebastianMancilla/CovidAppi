package com.example.covidapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.R
import com.example.covidapp.models.Status_DataItem
import kotlinx.android.synthetic.main.item_statuscard.view.*

class Status_Adapter(val context: Context, private val statusList: List<Status_DataItem>):RecyclerView.Adapter<Status_Adapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var coutry:TextView = itemView.tvCountry
        var confirmeds:TextView = itemView.tvConfirmados
        var deads:TextView = itemView.tvDeads
        var recovereds:TextView = itemView.tvRecovereds

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView =  LayoutInflater.from(context).inflate(R.layout.item_statuscard, parent,false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coutry.text = statusList[position].country
       holder.confirmeds.text = statusList[position].confirmed.toString()
        holder.deads.text = statusList[position].deaths.toString()
        holder.recovereds.text = statusList[position].recovered.toString()
    }

    override fun getItemCount(): Int = statusList.size

}