package com.example.u_health.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.diseodatos.DatePickerFragment
import com.example.u_health.R
import com.example.u_health.TimePickerFragment
import com.example.u_health.databinding.FragmentCitasBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding
import java.util.Calendar


class FragmentCitas : Fragment()
{

    private var _binding: FragmentCitasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCitasBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnAddCita.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.fragmentAddCita)

            binding.btnAddCita.visibility = View.INVISIBLE
        }






        return view
    }



}