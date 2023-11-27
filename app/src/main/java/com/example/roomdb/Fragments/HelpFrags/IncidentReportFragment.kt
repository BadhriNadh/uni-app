package com.example.roomdb.Fragments.HelpFrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.roomdb.R
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.databinding.FragmentIncidentReportFormBinding
import com.example.roomdb.entities.IncidentReport
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class IncidentReportFragment : Fragment() {

    private var _binding: FragmentIncidentReportFormBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIncidentReportFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            val studentName = binding.studentNameEditText.text.toString()
            val incidentTime = "${binding.incidentTimePicker.hour}:${binding.incidentTimePicker.minute}"
            val incidentDate = "${binding.incidentDatePicker.year}-${binding.incidentDatePicker.month + 1}-${binding.incidentDatePicker.dayOfMonth}"
            val incidentLocation = binding.incidentLocationEditText.text.toString()
            val incidentDescription = binding.incidentDescriptionEditText.text.toString()

            if (studentName.isNotEmpty() && incidentTime.isNotEmpty() &&
                incidentDate.isNotEmpty() && incidentLocation.isNotEmpty() &&
                incidentDescription.isNotEmpty()
            ) {
                val incidentReport = IncidentReport(
                    id = 0,
                    studentName = studentName,
                    incidentTime = incidentTime,
                    incidentDate = incidentDate,
                    incidentLocation = incidentLocation,
                    incidentDescription = incidentDescription
                )


                saveIncidentReport(incidentReport)

                Toast.makeText(
                    this.context,
                    "Report Submission is successful" ,
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun saveIncidentReport(incidentReport: IncidentReport) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {

                val database = AppDatabase.getDatabase(requireContext())


                database.incidentReportDao().insertIncidentReport(incidentReport)
            }


            clearInputFields()
        }
    }

    private fun clearInputFields() {
        binding.studentNameEditText.text.clear()
        binding.incidentLocationEditText.text.clear()
        binding.incidentDescriptionEditText.text.clear()


        binding.incidentTimePicker.hour = 0
        binding.incidentTimePicker.minute = 0

        val calendar = Calendar.getInstance()
        binding.incidentDatePicker.updateDate(
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
