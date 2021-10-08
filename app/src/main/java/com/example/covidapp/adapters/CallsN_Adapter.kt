package com.example.covidapp.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.R
import com.example.covidapp.models.Calls_Numbers_DataItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_callscards.view.*

class CallsN_Adapter(val context: Context, private val callsList: List<Calls_Numbers_DataItem>):RecyclerView.Adapter<CallsN_Adapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var cardCall: CardView = itemView.cardCalls
        var imageCN: ImageView = itemView.ivCallNumber
        var titlephone: TextView = itemView.tvTitleP
        var phonenumber: TextView = itemView.tvPhoneN


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_callscards, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(callsList[position].icon).into(holder.imageCN)
        holder.titlephone.text = callsList[position].title
        holder.phonenumber.text = callsList[position].phone

        holder.cardCall.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: " + callsList[position].phone)
            }
            startActivity(context,intent, Bundle.EMPTY)

        }


    }

    override fun getItemCount(): Int = callsList.size

}