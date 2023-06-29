package com.example.u_health.fragmentos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.u_health.R
import com.example.u_health.TimePickerFragment
import com.example.u_health.databinding.FragmentFrequencyBinding
import com.example.u_health.databinding.FragmentRecordatoriosBinding
import com.example.u_health.databinding.VistaFrecuenciaBinding
import com.example.u_health.databinding.VistaFrecuenciaDosisBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Frequency : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var cantidad : String
    private lateinit var popupWindow: PopupWindow
    private var _binding: FragmentFrequencyBinding? = null
    private val binding get() = _binding!!

    private var _bindingVF: VistaFrecuenciaBinding? = null
    private val bindingVF get() = _bindingVF!!
    private var _bindingVFD: VistaFrecuenciaDosisBinding? = null
    private val bindingVFD get() = _bindingVFD!!


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
        _binding = FragmentFrequencyBinding.inflate(inflater, container, false)
        _bindingVF = VistaFrecuenciaBinding.inflate(inflater, container, false)
        _bindingVFD = VistaFrecuenciaDosisBinding.inflate(inflater, container, false)
        val view = binding.root

        val valor = arguments?.getString("selectedItem")
        val bundle = Bundle()
        bundle.putString("valor", valor)
        if (valor != null) {
            binding.label.text = valor
            initNumberPicker()
            valida()
        }
        bindingVFD.btnAceptar.setOnClickListener {
            cantidad = bindingVFD.cantidadCapsules.text.toString()
            binding.txtDosis.setText("$cantidad")
            popupWindow.dismiss()
        }

        binding.btnSiguiente.setOnClickListener {
            if(!binding.txtHora.text.equals("Hora")&&
                !binding.txtDosis.text.equals("Dosis")){
                Toast.makeText(requireContext(), "Guardado", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireContext(), "Rellene los datos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    private fun valida() {
        actividadTwoNumberPicker(
            bindingVF.numberPickerHora,
            bindingVF.numberPickerMinutos,
            bindingVF.textviewPies,
            bindingVF.textviewPulgadas,
            binding.txtHora,
            bindingVF.alturaVista
        )
        dosis()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun dosis(){
        binding.txtDosis.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val touchX = event.x.toInt() // Posición X del evento táctil
                val imageWidth = binding.txtDosis.compoundDrawablesRelative[2]?.bounds?.width() ?: 0 // Ancho de la imagen

                // Ajustar las coordenadas para la nueva posición del TextView
                val textViewEndPosition = binding.txtDosis.width
                val touchThreshold = textViewEndPosition - imageWidth

                // Verificar si el evento táctil ocurrió dentro de la región de la imagen
                if (touchX >= binding.txtDosis.width - touchThreshold) {
                    showWindowFloat(0.89,bindingVFD.root)
                }
            }
            true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showWindowFloat(width: Double, popupView: View) {
        val widthPixels = (resources.displayMetrics.widthPixels * width).toInt()
        val heightPixels = (resources.displayMetrics.heightPixels * 0.4).toInt()
        //condicion para verificar si el popupView tiene una vsta asignada
        if (popupView.parent != null) {
            (popupView.parent as ViewGroup).removeView(popupView)
        }
        popupWindow = PopupWindow(popupView, widthPixels, heightPixels, true)
        popupWindow.showAtLocation(popupView.rootView, Gravity.CENTER, 0, 0)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun actividadTwoNumberPicker(numberPicker1: NumberPicker, numberPicker2: NumberPicker,
                                         textview1: TextView, textview2: TextView, TextViewActividadPeso: TextView
                                         , pesoVista: ConstraintLayout
    ) {

        // Actualiza el TextView mientras deslizas el NumberPicker1
        numberPicker1.setOnValueChangedListener { _, _, newVal ->
            textview1.text = newVal.toString()
        }

        // Actualiza el TextView mientras deslizas el NumberPicker2
        numberPicker2.setOnValueChangedListener { _, _, newVal ->
            textview2.text = newVal.toString()
        }
        TextViewActividadPeso.setOnTouchListener { _, event ->
            codeClick(pesoVista,event,TextViewActividadPeso)
        }
    }
    private fun codeClick(vista: ConstraintLayout, event: MotionEvent,
                          TextViewActividad: TextView
    ): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val touchX = event.x.toInt() // Posición X del evento táctil
            val imageWidth = TextViewActividad.compoundDrawablesRelative[2]?.bounds?.width() ?: 0 // Ancho de la imagen

            // Ajustar las coordenadas para la nueva posición del TextView
            val textViewEndPosition = TextViewActividad.width
            val touchThreshold = textViewEndPosition - imageWidth

            // Verificar si el evento táctil ocurrió dentro de la región de la imagen
            if (touchX >= TextViewActividad.width - touchThreshold) {
                vista.visibility = View.VISIBLE
                showTimePicker()
            }
        }
        return true
    }
    private fun initNumberPicker() {

        //altura
        bindingVF.numberPickerHora.minValue = 1
        bindingVF.numberPickerHora.maxValue = 24
        bindingVF.numberPickerMinutos.minValue = 0
        bindingVF.numberPickerMinutos.maxValue = 60

    }
    private fun showTimePicker(){
        val timepicker = TimePickerFragment{currentDate(it)}
        val fragmentManager = requireActivity().supportFragmentManager
        timepicker.show(fragmentManager, "timepicker")
    }

    private fun currentDate(time : String) {
        binding.txtHora.text = "$time"

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String,selectedItem: String) =
            Frequency().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString("selectedItem", selectedItem)
                }
            }
    }
}