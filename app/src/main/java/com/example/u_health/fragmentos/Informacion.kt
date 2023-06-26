package com.example.u_health.fragmentos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.u_health.R
import com.example.u_health.databinding.FragmentInformacionBinding
import com.example.u_health.databinding.FragmentMapaBinding
import com.example.u_health.informacion_medicamentos

private var fbinding: FragmentInformacionBinding? = null
private val binding get() = fbinding!!

class Informacion : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fbinding = FragmentInformacionBinding.inflate(inflater, container, false)
        val view: View = binding.root
        binding.btnanalgesico.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java)
            startActivity(intent)
        }
        return view
    }




}