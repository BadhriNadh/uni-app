package com.example.roomdb.Fragments.HelpFrags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.entities.Accessibility

class AccessibilityAdapter :
    ListAdapter<Accessibility, AccessibilityAdapter.AccessibilityViewHolder>(AccessibilityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessibilityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accessibility, parent, false)
        return AccessibilityViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccessibilityViewHolder, position: Int) {
        val accessibility = getItem(position)
        holder.bind(accessibility)
    }

    inner class AccessibilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textStudentName: TextView = itemView.findViewById(R.id.textStudentName)
        private val textStudentId: TextView = itemView.findViewById(R.id.textStudentId)
        private val textRequestType: TextView = itemView.findViewById(R.id.textRequestType)
        private val textTime: TextView = itemView.findViewById(R.id.textTime)
        private val textDate: TextView = itemView.findViewById(R.id.textDate)
        private val textPurpose: TextView = itemView.findViewById(R.id.textPurpose)

        fun bind(accessibility: Accessibility) {
            textStudentName.text = "Student Name: ${accessibility.studentName}"
            textStudentId.text = "Student ID: ${accessibility.studentId}"
            textRequestType.text = "Request Type: ${accessibility.requestType}"
            textTime.text = "Time: ${accessibility.time}"
            textDate.text = "Date: ${accessibility.date}"
            textPurpose.text = "Purpose: ${accessibility.purpose}"
        }
    }

    private class AccessibilityDiffCallback : DiffUtil.ItemCallback<Accessibility>() {
        override fun areItemsTheSame(oldItem: Accessibility, newItem: Accessibility): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Accessibility, newItem: Accessibility): Boolean {
            return oldItem == newItem
        }
    }
}
