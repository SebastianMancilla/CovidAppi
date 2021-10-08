package com.example.covidapp.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.covidapp.R
import com.example.covidapp.interfaces.Maps_ApiInterface
import com.example.covidapp.models.Maps_DataItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var map:GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createFragment()

    }

    /*createFragment()
    * Initialized fragment map*/
    private fun createFragment() {
        //google mapsKey in values google_maps_api.xml
        val mapFragment:SupportMapFragment = childFragmentManager.findFragmentById(R.id.frMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true

        enableMyLocation()// location actual from user
        createMarker()// create Markers
        val gtCity= LatLng(14.635352660844836, -90.50921211383472) // start position view in the map
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(gtCity, 9.5f),
            4000,
            null
        )

        //enableLocation()

    }

    //petition of Permission location
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireActivity(),
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    //petition of Permission location
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }
        companion object{
           const val  LOCATION_REQUEST_CODE = 0
        }
    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(requireActivity(), "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_REQUEST_CODE)
        }
    }

    //petition of Permission location
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            LOCATION_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                    return
                }
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
            }
        }

    }

    /* createMarker()
    * in the function created markers of bd and displayed on map*/
    private fun createMarker() {
        // call retrofitBuilder an creation
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://gtpreviene.researchmobile.co:3000/api/")//baseUrl
            .build()
            .create(Maps_ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCentresMarkers()


        retrofitData.enqueue(object : Callback<List<Maps_DataItem>>{
            override fun onResponse(call: Call<List<Maps_DataItem>>, response: Response<List<Maps_DataItem>>) {
                val responseBody = response.body()!!

                initMarker(responseBody)
            }

            override fun onFailure(call: Call<List<Maps_DataItem>>, t: Throwable) {
                Toast.makeText(requireActivity(),"Problems Detected on Create Markers",Toast.LENGTH_SHORT).show()

            }

        })

    }

    /* init markers function
    * in the function created markers of bd and displayed on map*/
    private fun initMarker(responseBody: List<Maps_DataItem>) {
        for (mark in responseBody){
            val latLng = LatLng(mark.latitude.toDouble(), mark.longitude.toDouble())
            val title = mark.name
            val place = mark.description
            val markerOptions = MarkerOptions() //add markers Options
                .position(latLng)
                .title(title)
                .snippet(place)

            map.addMarker(markerOptions)//add marker

        }

    }


}


