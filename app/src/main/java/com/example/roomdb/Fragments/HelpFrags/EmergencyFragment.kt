package com.example.roomdb.Fragments.HelpFrags

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EmergencyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmergencyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var emergencyContactsRecyclerView: RecyclerView
    private lateinit var emergencyContactsAdapter: EmergencyContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_emergency_list, container, false)

        emergencyContactsRecyclerView = view.findViewById(R.id.emergencyContactsRecyclerView)
        emergencyContactsAdapter = EmergencyContactsAdapter()

        // For example, you can dynamically add emergency contacts to the adapter
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 1", "123456789"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 2", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 3", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 4", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 5", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 6", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 7", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 8", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 9", "987654321"))
        emergencyContactsAdapter.addEmergencyContact(EmergencyContact("Emergency Contact 10", "987654321"))

        emergencyContactsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        emergencyContactsRecyclerView.adapter = emergencyContactsAdapter

        return view
    }

    // Inner class for the custom adapter
    inner class EmergencyContactsAdapter :
        RecyclerView.Adapter<EmergencyContactsAdapter.EmergencyContactViewHolder>() {

        private val emergencyContacts = mutableListOf<EmergencyContact>()

        fun addEmergencyContact(emergencyContact: EmergencyContact) {
            emergencyContacts.add(emergencyContact)
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): EmergencyContactViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_emergency_contact, parent, false)
            return EmergencyContactViewHolder(view)
        }

        override fun onBindViewHolder(holder: EmergencyContactViewHolder, position: Int) {
            val contact = emergencyContacts[position]
            holder.bind(contact)
        }

        override fun getItemCount(): Int {
            return emergencyContacts.size
        }

        inner class EmergencyContactViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            private val nameTextView: TextView = itemView.findViewById(R.id.contactName)
            private val callButton: Button = itemView.findViewById(R.id.callButton)

            fun bind(emergencyContact: EmergencyContact) {
                nameTextView.text = emergencyContact.name
                callButton.setOnClickListener {
                    // Handle the call button click by initiating a phone call
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${emergencyContact.phoneNumber}"))
                    startActivity(intent)
                }
            }
        }

    }
}