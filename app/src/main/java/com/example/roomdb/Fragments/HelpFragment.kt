package com.example.roomdb.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.roomdb.Fragments.HelpFrags.AccessibilityFragment
import com.example.roomdb.Fragments.HelpFrags.EmergencyFragment
import com.example.roomdb.Fragments.HelpFrags.HistoryFragment
import com.example.roomdb.Fragments.HelpFrags.IncidentReportFragment
import com.example.roomdb.R
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HelpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HelpFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//    private lateinit var simpleFrameLayout: FrameLayout
//    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        // get the reference of FrameLayout and TabLayout
        val simpleFrameLayout: FrameLayout = view.findViewById(R.id.simpleFrameLayout)
        val tabLayout: TabLayout = view.findViewById(R.id.simpleTabLayout)

        val fragment: Fragment? =  EmergencyFragment()
            fragment?.let {
                val fm = (activity as FragmentActivity).supportFragmentManager
                val ft: FragmentTransaction = fm.beginTransaction()
                ft.replace(R.id.simpleFrameLayout, it)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ft.commit()
            }


        // Create a new Tab named "First"
        val emergencyTab = tabLayout.newTab()
        emergencyTab.text = "Emergency" // set the Text for the first Tab
        emergencyTab.setIcon(R.drawable.emergency_icon_24) // set an icon for the first tab
        tabLayout.addTab(emergencyTab) // add the tab at in the TabLayout

        // Create a new Tab named "Second"
        val incidentTab = tabLayout.newTab()
        incidentTab.text = "Report"// set the Text for the second Tab

        incidentTab.setIcon(R.drawable.incident_report_icon_24) // set an icon for the second tab
        tabLayout.addTab(incidentTab) // add the tab in the TabLayout

        // Create a new Tab named "Third"
        val accessibilityTab = tabLayout.newTab()
        accessibilityTab.text = "Request" // set the Text for the first Tab
        accessibilityTab.setIcon(R.drawable.accessibility_icon_24) // set an icon for the first tab
        tabLayout.addTab(accessibilityTab) // add the tab at in the TabLayout

        // Create a new Tab named "Fourth"
        val historyTab = tabLayout.newTab()
        historyTab.text = "History" // set the Text for the first Tab
        historyTab.setIcon(R.drawable.icon_history_24) // set an icon for the first tab
        tabLayout.addTab(historyTab) // add the tab at in the TabLayout

        // perform setOnTabSelectedListener event on TabLayout
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // get the current selected tab's position and replace the fragment accordingly
                val fragment: Fragment? = when (tab.position) {
                    0 -> EmergencyFragment()
                    1 -> IncidentReportFragment()
                    2 -> AccessibilityFragment()
                    3 -> HistoryFragment()
                    else -> null
                }

                fragment?.let {
                    val fm = (activity as FragmentActivity).supportFragmentManager
                    val ft: FragmentTransaction = fm.beginTransaction()
                    ft.replace(R.id.simpleFrameLayout, it)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ft.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return view
    }


}