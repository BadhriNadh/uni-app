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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val callback = OnMapReadyCallback { googleMap ->
        val dal = LatLng(44.636482779636395, -63.59052392610978)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dal, 15f))

        // Add 10 markers with different colors
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63762995980053, -63.59115155668311))
                .title("Killam Memorial Library")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.637642990629566, -63.587395703425905))
                .title("Goldberg Computer Science Building")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63690144894614, -63.58892963438764))
                .title("Dalhousie Student Union")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63885711223773, -63.59136393980397))
                .title("DalCard Office")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.636087342033996, -63.593132717007215))
                .title("Registrar's Office")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        )
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63602004359928, -63.58959893938525))
                .title("Dalhousie International Centre")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63781880916997, -63.59339594666409))
                .title("Dunn Building")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.637134929574245, -63.593326743542185))
                .title("Chase Building")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.637101525924955, -63.59099663774322))
                .title("Mark A. Hill Accessibility Centre")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.637581321982516, -63.58958833196182))
                .title("Marion McCain Arts and Social Sciences Building")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.63789713494205, -63.588517166076116))
                .title("Dalhousie Art Gallery")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.638042894189965, -63.587475873382125))
                .title("Schulich School of Law at Dalhousie University")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(44.637116709405745, -63.588564109608704))
                .title("Dalhousie University - Kenneth C. Rowe Management Bldg")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )

        if(!checkLocationPermission()){
            requestLocationPermission()
        }else{
            markUser(googleMap)
        }
    }

    private fun markUser(googleMap: GoogleMap) {

        if(!checkLocationPermission()){
            requestLocationPermission()
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        val userIcon =
                            BitmapDescriptorFactory.fromResource(android.R.drawable.btn_star_big_on)
                        val userLocation = LatLng(latitude, longitude)
                        googleMap.addMarker(
                            MarkerOptions()
                                .position(userLocation)
                                .title("You")
                                .icon(userIcon)
                        )
                    }
                }
        }
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
