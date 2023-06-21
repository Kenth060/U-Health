package com.example.u_health.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.u_health.R
import com.example.u_health.databinding.FragmentMapaBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment


class Mapa : Fragment() , OnMapReadyCallback {


    private var fbinding: FragmentMapaBinding? = null

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false)
        createMapFragment()
    }
    private fun createMapFragment() {
        val mapFragment =parentFragmentManager.findFragmentById(R.id.fragment_mapas) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMaps: GoogleMap) {
        map = googleMaps
    }

}