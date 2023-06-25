package com.example.u_health.fragmentos

import android.Manifest
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.u_health.R
import com.example.u_health.databinding.FragmentMapaBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.contracts.ReturnsNotNull


class Mapa : Fragment() , OnMapReadyCallback {


    private var fbinding: FragmentMapaBinding? = null
    private val binding get() = fbinding!!
    private lateinit var MAP:GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fbinding = FragmentMapaBinding.inflate(inflater, container, false)
        val view: View = binding.root


        CreateMapFragment()
        return view
    }


    private fun CreateMapFragment()
    {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap)
    {
        MAP=googleMap
        MAP.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(),R.raw.mapa_style))

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        MAP.isMyLocationEnabled=true




        val zoom = 16f
        val miubicacion = LatLng(12.11170942302386, -86.23239262022197)
        MAP?.animateCamera(CameraUpdateFactory.newLatLngZoom(miubicacion, zoom))

        MAP.setOnPoiClickListener {

            Toast.makeText(requireContext(), "Abriendo Navegacion Paso a Paso", Toast.LENGTH_SHORT).show()


            val gmmIntentUri = Uri.parse("google.navigation:q=${it.latLng.latitude},${it.latLng.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW,gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }

        val permiso=true

        MAP.uiSettings.isMapToolbarEnabled=permiso
        MAP.uiSettings.isCompassEnabled=permiso
        MAP.uiSettings.isScrollGesturesEnabled=permiso
        MAP.uiSettings.isZoomControlsEnabled=permiso
    }


}