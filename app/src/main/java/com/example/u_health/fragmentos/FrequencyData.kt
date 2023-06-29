package com.example.u_health.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.u_health.R
import com.example.u_health.databinding.FragmentDatosSearchBinding
import com.example.u_health.databinding.FragmentFrequencyDataBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FrequencyData : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFrequencyDataBinding? = null
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

        _binding= FragmentFrequencyDataBinding.inflate(inflater,container,false)
        val view = binding.root

        val valor = arguments?.getString("selectedItem")
        val bundle = Bundle()
        bundle.putString("valor", valor)
        if (valor != null) {
            binding.medicamentoSelect.text = valor
        }
        binding.btnGuardar.setOnClickListener {
            if(binding.radioButton1.isChecked || binding.radioButton2.isChecked || binding.radioButton3.isChecked){

                val fragment = Frequency()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentData, fragment)
                    .commit()

            }else{
                Toast.makeText(requireContext(), "Rellene los datos Juan dundo", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String,selectedItem: String) =
            FrequencyData().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString("selectedItem", selectedItem)
                }
            }
    }
}