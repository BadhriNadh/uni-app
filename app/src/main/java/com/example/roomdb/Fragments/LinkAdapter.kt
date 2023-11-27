package com.example.roomdb.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.entities.Link
import com.bumptech.glide.Glide
// Adapter for displaying a list of links in a RecyclerView

class LinkAdapter(private val links: List<Link>, private val onLinkClickListener: (String) -> Unit) :
    RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {

    // ViewHolder for individual link items
    inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linkImage: ImageView = itemView.findViewById(R.id.linkImage)
        private val linkTitle: TextView = itemView.findViewById(R.id.linkTitle)

        // Binding data to views within each item

        fun bind(link: Link) {
            linkTitle.text = link.title
            // Load images using Glide library based on the type of image (resource or URL)
            if (link.image is Int) {
                // If the image is a resource (Int), load it using the resource ID
                Glide.with(itemView)
                    .load(link.image as Int)
                    .placeholder(R.drawable.askus)
                    .error(R.drawable.image)
                    .into(linkImage)
            } else  {
                // If the image is a URL (String), load it using the provided URL
                Glide.with(itemView)
                    .load(link.image as String)
                    .placeholder(R.drawable.campuslife)
                    .error(R.drawable.academics)
                    .into(linkImage)
            }
            // Set click listener to handle link item clicks
            itemView.setOnClickListener { onLinkClickListener.invoke(link.url) }
        }
    }
    // Create a new ViewHolder instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        // Inflate the layout for a link item view

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        return LinkViewHolder(itemView)
    }
    // Bind data to the ViewHolder

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.bind(links[position])
    }
    // Return the total number of items in the data set

    override fun getItemCount(): Int = links.size
}

