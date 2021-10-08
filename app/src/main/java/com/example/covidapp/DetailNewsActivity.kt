package com.example.covidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_news.*
import org.sufficientlysecure.htmltextview.HtmlTextView

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        fbDetail.setOnClickListener{
            onBackPressed()

        }

        getAndShowData()

    }

    private fun getAndShowData(){
        val bundle = Bundle(intent.extras)
        val titleNews = bundle.get("TITLE_NEWS")
        val textNews = bundle.get("DETAIL_NEWS")
        val imageNews = bundle.get("IMAGE_NEWS")

        val tvTitleDNews:TextView = findViewById(R.id.tvTitleDNews)
        val tvTextNewsCard:HtmlTextView = findViewById(R.id.tvTextNewsCard)
        val ivDetailNews:ImageView = findViewById(R.id.ivDetailNews)

        tvTitleDNews.text = "$titleNews"
        tvTextNewsCard.setHtml("$textNews")
        Picasso.get().load(imageNews.toString()).into(ivDetailNews)


    }
}