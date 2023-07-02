package com.example.u_health.fragmentos

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.u_health.R
import com.example.u_health.databinding.FragmentCitasBinding
import com.example.u_health.databinding.FragmentRecordatorioMedicoBinding

class fragment_Recordatorio_Medico : Fragment()
{


    private var _binding: FragmentRecordatorioMedicoBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        _binding = FragmentRecordatorioMedicoBinding.inflate(inflater, container, false)
        val view = binding.root

        val Prefencias = context?.getSharedPreferences("Datos_Citas", Context.MODE_PRIVATE)
        val Titulo = Prefencias?.getString("Titulo", "")
        val Medico = Prefencias?.getString("Medico", "")
        val Fecha = Prefencias?.getString("Fecha", "")
        val Hora = Prefencias?.getString("Hora", "")
        val Detalles = Prefencias?.getString("Detalles", "")

        val editor = Prefencias?.edit()
        editor?.clear()
        editor?.apply()

        binding.txtInfoDr.text=Medico
        binding.txtTitulo.text=Titulo
        binding.txtFechaCitaDet.text=Fecha
        binding.txtHoraDet.text=Hora
        binding.txtCitaInfo.text=Detalles


        return view
    }
}