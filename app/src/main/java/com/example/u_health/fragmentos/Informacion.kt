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

private val opc="opc"

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

        binding.btnAnalgesico.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","1") }
            startActivity(intent) }

        binding.btnAntiflamatorio.setOnClickListener{
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","2") }
            startActivity(intent) }

        binding.btnAntibioticos.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","3") }
            startActivity(intent) }

        binding.btnAntidepresivos.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","4") }
            startActivity(intent) }

        binding.btnDiabetes.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","5") }
            startActivity(intent) }

        binding.btnMedicinaTos.setOnClickListener {
            val intent = Intent(requireContext(), informacion_medicamentos::class.java).apply { putExtra("opc","6") }
            startActivity(intent) }





        return view
    }




}