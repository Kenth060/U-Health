package com.example.u_health.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.u_health.FragmentIMC
import com.example.u_health.R
import com.example.u_health.databinding.FragmentRecordatoriosBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Recordatorios : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentRecordatoriosBinding? = null
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
        _binding = FragmentRecordatoriosBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnAdd.setOnClickListener {
            //val fragment = FragmentDatosSearch()
            val fragment = FragmentIMC()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fgRecordatorio, fragment)
                .commit()

            binding.btnAdd.visibility = View.INVISIBLE
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Recordatorios().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}