package com.example.roomdb.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.roomdb.Fragments.MapFragments.MapsFragment
import com.example.roomdb.Fragments.MapFragments.TransitFragment
import com.example.roomdb.R
import com.google.android.material.tabs.TabLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RoutesFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_routes, container, false)

        val tabLayout: TabLayout = view.findViewById(R.id.simpleTabLayout)

        val mapTab = tabLayout.newTab()
        mapTab.text = "Map"
        mapTab.setIcon(R.drawable.baseline_map_24)
        tabLayout.addTab(mapTab, true)

        val transitTab = tabLayout.newTab()
        transitTab.text = "Transit"
        transitTab.setIcon(R.drawable.baseline_directions_bus_24)
        tabLayout.addTab(transitTab)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment: Fragment? = when (tab.position) {
                    0 -> MapsFragment()
                    1 -> TransitFragment()
                    else -> null
                }

                fragment?.let {
                    val fm = (activity as FragmentActivity).supportFragmentManager
                    val ft: FragmentTransaction = fm.beginTransaction()
                    ft.replace(R.id.fragmentContainer, it)
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ft.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // Default fragment when "RoutesFragment" is opened
        val defaultFragment = MapsFragment()
        val fm = (activity as FragmentActivity).supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.fragmentContainer, defaultFragment)
        ft.commit()

        return view
    }
}
