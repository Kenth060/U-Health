package com.example.u_health.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.Navigation
import com.example.u_health.R
import com.example.u_health.databinding.FragmentDatosSearchBinding
import com.example.u_health.databinding.FragmentFrequencyDataBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FrequencyData : Fragment() {


    private var _binding: FragmentFrequencyDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            if(binding.radioButton1.isChecked || binding.radioButton2.isChecked || binding.radioButton3.isChecked)
            {

                Navigation.findNavController(view).navigate(R.id.frequency)

            }else{
                Toast.makeText(requireContext(), "Rellene los datos Juan dundo", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }


}