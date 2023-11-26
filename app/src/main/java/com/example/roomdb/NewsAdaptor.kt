package com.example.roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class NewsAdaptor(private val newsList:ArrayList<News>)
    :RecyclerView.Adapter<NewsAdaptor.NewsViewHolder>(){


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView=itemView.findViewById(R.id.newsImageView)
        val textView: TextView=itemView.findViewById(R.id.newsTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.home_news,parent,false )
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news=newsList[position]
        holder.imageView.setImageResource(news.image)
        holder.textView.text=news.name

    }
}