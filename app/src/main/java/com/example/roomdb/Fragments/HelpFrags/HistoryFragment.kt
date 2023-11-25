package com.example.roomdb.Fragments.HelpFrags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.database.AccessibilityDao
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.databinding.FragmentHistoryBinding
import com.example.roomdb.entities.Accessibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var accessibilityAdapter: AccessibilityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accessibilityAdapter = AccessibilityAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = accessibilityAdapter
        }

        loadAccessibilityRequests()
    }

    private fun loadAccessibilityRequests() {
        GlobalScope.launch(Dispatchers.Main) {
            val accessibilityList = withContext(Dispatchers.IO) {
                val database = AppDatabase.getDatabase(requireContext())
                database.accessibilityDao().getAllAccessibilities()
            }

            accessibilityAdapter.submitList(accessibilityList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
