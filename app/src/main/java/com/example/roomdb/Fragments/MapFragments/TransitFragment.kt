package com.example.roomdb.Fragments.MapFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.busInfo.RetrofitClient
import com.example.roomdb.busInfo.TimingApiResponse
import com.example.roomdb.busInfo.TimingViewModel
import com.example.roomdb.busInfo.TimingsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TransitFragment : Fragment() {

    private val timingsApiService: TimingsApiService by lazy {
        RetrofitClient.instance.create(TimingsApiService::class.java)
    }
    private lateinit var timingViewModel: TimingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_transit, container, false)
        timingViewModel = ViewModelProvider(this)[TimingViewModel::class.java]

        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("HHmm", Locale.getDefault())
        val formattedTime = sdf.format(Date(currentTime))

        val call = timingsApiService.getTiming(formattedTime.toInt())
        call.enqueue(object : Callback<TimingApiResponse> {
            override fun onResponse(
                call: Call<TimingApiResponse>,
                response: Response<TimingApiResponse>
            ) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val timings = apiResponse?.body
                    timingViewModel.clearTimings()
                    if (timings != null) {
                        timingViewModel.insertTimings(timings)
                    }
                } else {
                    Log.e("API", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<TimingApiResponse>, t: Throwable) {
                // Handle failure
                Log.e("API", "Network error: ${t.message}")
            }
        })

        timingViewModel.timings.observe(this, Observer { timings ->

            val stopOneTimings = timings.filter { timing -> timing.number == "10" || timing.number == "4" }
            val stopTwoTimings = timings.filter { timing -> timing.number == "1"  }
            val stopThreeTimings = timings.filter { timing -> timing.number == "7" }

            val timingsOneAdapter = TimingsAdapter(stopOneTimings)
            val recyclerViewOne: RecyclerView = view.findViewById(R.id.NewsRecyclerView1)
            recyclerViewOne.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewOne.adapter = timingsOneAdapter

            val timingsTwoAdapter = TimingsAdapter(stopTwoTimings)
            val recyclerViewTwo: RecyclerView = view.findViewById(R.id.NewsRecyclerView2)
            recyclerViewTwo.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewTwo.adapter = timingsTwoAdapter

            val timingsThreeAdapter = TimingsAdapter(stopThreeTimings)
            val recyclerViewThree: RecyclerView = view.findViewById(R.id.NewsRecyclerView3)
            recyclerViewThree.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewThree.adapter = timingsThreeAdapter
        })

        timingViewModel.getAllTimings()

        return view
    }
}
