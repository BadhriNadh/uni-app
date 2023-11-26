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
class LinkAdapter(private val links: List<Link>, private val onLinkClickListener: (String) -> Unit) :
    RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {

    inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val linkImage: ImageView = itemView.findViewById(R.id.linkImage)
        private val linkTitle: TextView = itemView.findViewById(R.id.linkTitle)

        fun bind(link: Link) {
            linkTitle.text = link.title
            if (link.image is Int) {
                Glide.with(itemView)
                    .load(link.image as Int)
                    .placeholder(R.drawable.askus)
                    .error(R.drawable.library)
                    .into(linkImage)
            } else  {
                Glide.with(itemView)
                    .load(link.image as String)
                    .placeholder(R.drawable.campuslife)
                    .error(R.drawable.academics)
                    .into(linkImage)
            }

            itemView.setOnClickListener { onLinkClickListener.invoke(link.url) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
        return LinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
        holder.bind(links[position])
    }

    override fun getItemCount(): Int = links.size
}


//works
//class LinkAdapter(private val links: List<Link>, private val onLinkClickListener: (String) -> Unit) :
//    RecyclerView.Adapter<LinkAdapter.LinkViewHolder>() {
//
//    inner class LinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
//
//        fun bind(link: Link) {
//            titleTextView.text = link.title
//            itemView.setOnClickListener { onLinkClickListener.invoke(link.url) }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
//        val itemView =
//            LayoutInflater.from(parent.context).inflate(R.layout.item_link, parent, false)
//        return LinkViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
//        holder.bind(links[position])
//    }
//
//    override fun getItemCount(): Int = links.size
//}
