package com.example.roomdb.Fragments.HelpFrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.roomdb.R
import com.example.roomdb.database.AccessibilityDao
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.databinding.FragmentAccessibilityFormBinding
import com.example.roomdb.entities.Accessibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AccessibilityFragment : Fragment() {

    // Add an array of request types
    private val requestTypes = arrayOf("Exam Accomodation", "Laptop Loans", "Writing Assistant", "Teaching Assistant", "Grocery")
    private var _binding: FragmentAccessibilityFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccessibilityFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ArrayAdapter with the array of request types
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, requestTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the Spinner
        binding.requestTypeSpinner.adapter = adapter

        binding.submitButton.setOnClickListener {
            val studentName = binding.studentNameEditText.text.toString()
            val studentId = binding.studentIdEditText.text.toString()
            val requestType = binding.requestTypeSpinner.selectedItem.toString()
            val time = "${binding.timePicker.hour}:${binding.timePicker.minute}"
            val date = "${binding.datePicker.year}-${binding.datePicker.month + 1}-${binding.datePicker.dayOfMonth}"
            val purpose = binding.purposeEditText.text.toString()

            if (studentName.isNotEmpty() && studentId.isNotEmpty() &&
                requestType.isNotEmpty() && time.isNotEmpty() &&
                date.isNotEmpty() && purpose.isNotEmpty()
            ) {
                val accessibility = Accessibility(
                    id = 0, // Auto-generated ID
                    studentName = studentName,
                    studentId = studentId,
                    requestType = requestType,
                    time = time,
                    date = date,
                    purpose = purpose
                )

                // Save the Accessibility to the Room Database
                saveAccessibility(accessibility)

                Toast.makeText(
                    this.context,
                    "Request Submission is successful" ,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun saveAccessibility(accessibility: Accessibility) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                // Get the Room Database instance
                val database = AppDatabase.getDatabase(requireContext())

                // Insert the Accessibility into the database
                database.accessibilityDao().insertAccessibility(accessibility)
            }

            // Clear the input fields after saving
            clearInputFields()
        }
    }

    private fun clearInputFields() {
        binding.studentNameEditText.text.clear()
        binding.studentIdEditText.text.clear()
        binding.requestTypeSpinner.setSelection(0)
        binding.purposeEditText.text.clear()

        // Set default values for time and date
        binding.timePicker.hour = 0
        binding.timePicker.minute = 0

        val calendar = Calendar.getInstance()
        binding.datePicker.updateDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
