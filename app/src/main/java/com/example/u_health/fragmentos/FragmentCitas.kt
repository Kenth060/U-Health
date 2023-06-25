package com.example.u_health.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.diseodatos.DatePickerFragment
import com.example.u_health.R
import com.example.u_health.TimePickerFragment
import com.example.u_health.databinding.FragmentCitasBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding
import java.util.Calendar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentCitas : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentCitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitasBinding.inflate(inflater, container, false)
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
    private fun showDatePickerDialog() {
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

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCitas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}