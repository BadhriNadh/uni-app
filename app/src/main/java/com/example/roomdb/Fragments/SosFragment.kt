package com.example.roomdb.Fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.roomdb.R
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sos, container, false)
//        val sendMsgBtn

        val sendMsgBtn: FloatingActionButton = view.findViewById(R.id.sosbtn)

        // adding on click listener for send message button.
        sendMsgBtn.setOnClickListener {

            // on below line we are creating two
            // variables for phone and message
            val phoneNumber = "+17828820796"
            val message = "test emergency message"

            // on the below line we are creating a try and catch block
            try {

                // on below line we are initializing sms manager.
                //as after android 10 the getDefault function no longer works
                //so we have to check that if our android version is greater
                //than or equal toandroid version 6.0 i.e SDK 23
//                val smsManager: SmsManager

                val smsManager: SmsManager = if (Build.VERSION.SDK_INT >= 23) {
                    //if SDK is greater that or equal to 23 then
                    //this is how we will initialize the SmsManager
                    requireContext().getSystemService(SmsManager::class.java)
                } else {
                    //if user's SDK is less than 23 then
                    //SmsManager will be initialized like this
//                    smsManager = SmsManager.getDefault()
                    SmsManager.getDefault()
                } ?: throw IllegalStateException("Failed to initialize SmsManager")
                Log.d("INFO","entered if part")

                // on below line we are sending text message.
//                smsManager.sendTextMessage(phoneNumber, null, message, null, null)

                // on below line we are displaying a toast message for message send,
                Toast.makeText(requireContext(), "Message Sent", Toast.LENGTH_LONG).show()
                makeEmergencyCall()

            } catch (e: Exception) {

                // on catch block we are displaying toast message for error.
                Toast.makeText(
                    this.context,
                    "Please enter all the data.." + e.message.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        }
        return view
    }

    private fun makeEmergencyCall() {
        // Check for permission before initiating a call.
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val emergencyNumber = "tel:9029893478"
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse(emergencyNumber)
            startActivity(callIntent)
        } else {
            // You may need to request the CALL_PHONE permission before making the call.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL_PERMISSION
            )
        }
    }

    companion object {
        private const val REQUEST_CALL_PERMISSION = 123
    }


}