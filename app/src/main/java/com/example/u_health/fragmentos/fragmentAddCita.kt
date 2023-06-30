package com.example.u_health.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.diseodatos.DatePickerFragment
import com.example.u_health.R
import com.example.u_health.TimePickerFragment
import com.example.u_health.databinding.FragmentAddCitaBinding

class fragmentAddCita : Fragment()
{
    private var _binding: FragmentAddCitaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCitaBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnCancelar.setOnClickListener {
            Toast.makeText(requireContext(), "cancelar", Toast.LENGTH_SHORT).show()
        }
        binding.fecha.setOnClickListener {
            showDatePickerDialog()
        }
        binding.hora.setOnClickListener {
            showTimePicker()
        }

        return view
    }

    private fun showDatePickerDialog()
    {
        val datePicker = DatePickerFragment { dia, mes, year -> currentDate(dia, mes, year) }
        datePicker.show(requireFragmentManager(), "datepicker")
    }
    private fun currentDate(dia: Int, mes: Int, year: Int) {
        binding.txtFecha.text = "$dia / $mes / $year"
    }
    private fun showTimePicker(){
        val timepicker = TimePickerFragment{onTimeSelected(it)}
        timepicker.show(requireFragmentManager(),"timepicker")
    }

    private fun onTimeSelected(time : String) {
        binding.txtHora.text = "$time"
    }
}