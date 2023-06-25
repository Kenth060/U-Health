package com.example.u_health.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.u_health.databinding.FragmentUsuarioBinding

class Usuario : Fragment() {



    private var _binding: FragmentUsuarioBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsuarioBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.txtUsuario.text="Kenneth"

        return view
    }

}