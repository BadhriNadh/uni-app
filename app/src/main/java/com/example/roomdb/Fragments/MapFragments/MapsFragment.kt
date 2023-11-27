package com.example.roomdb.Fragments.MapFragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.roomdb.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->
        val dal = LatLng(44.636482779636395, -63.59052392610978)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dal, 15f))
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        val shareButton: ImageButton = view.findViewById(R.id.button)
        shareButton.setOnClickListener {
            if (checkLocationPermission()) {
                shareCurrentLocation()
            } else {
                requestLocationPermission()
            }
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private fun checkLocationPermission(): Boolean {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), REQUEST_LOCATION_PERMISSION)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareCurrentLocation()
                }
            }
        }
    }
    private fun shareCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude

                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(
                            Intent.EXTRA_TEXT,
                            "Check out my current location: https://maps.google.com/maps?q=$latitude,$longitude"
                        )
                        type = "text/plain"
                    }

                    val chooserIntent = Intent.createChooser(shareIntent, "Share via")

                    if (shareIntent.resolveActivity(requireContext().packageManager) != null) {
                        startActivity(chooserIntent)
                    }
                }
            }
    }
}
