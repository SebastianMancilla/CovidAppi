package com.example.covidapp.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.DetailNewsActivity
import com.example.covidapp.R
import com.example.covidapp.models.News_DataItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news_card.view.*
import kotlinx.android.synthetic.main.item_news_card.view.tvTextNews


class News_Adapter(val context: Context, private val newsList: List<News_DataItem>): RecyclerView.Adapter<News_Adapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var cardNews: CardView = itemView.cvNews
        var image:ImageView = itemView.ivNotice
        var title:TextView = itemView.tvTextNews
        var category:TextView = itemView.tvNewCategory


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_news_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(newsList[position].image).into(holder.image)
        holder.title.text = newsList[position].title
        holder.category.text = newsList[position].category_id.toString()

        holder.cardNews.setOnClickListener {
            val intent = Intent(context,DetailNewsActivity::class.java)
                intent.putExtra("TITLE_NEWS",newsList[position].title)
                intent.putExtra("IMAGE_NEWS", newsList[position].image)
                intent.putExtra("DETAIL_NEWS", newsList[position].detail)

            ContextCompat.startActivity(context, intent, Bundle.EMPTY)


        }




    }

    override fun getItemCount(): Int = newsList.size



}