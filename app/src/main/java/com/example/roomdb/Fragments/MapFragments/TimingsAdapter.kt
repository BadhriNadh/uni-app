package com.example.roomdb.Fragments.MapFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.entities.Timing

class TimingsAdapter(private val articleEntities: List<Timing>) : RecyclerView.Adapter<TimingsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_bus, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = articleEntities[position]
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return articleEntities.size
    }

    inner class ViewHolder(timingsView: View) : RecyclerView.ViewHolder(timingsView) {
        private val numberTextView: TextView = timingsView.findViewById(R.id.numberTextView)
        private val timeTextView: TextView = timingsView.findViewById(R.id.timeTextView)


        fun bind(timing: Timing) {
            numberTextView.text = timing.number
            timeTextView.text = timing.time
        }
    }
}