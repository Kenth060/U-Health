package com.example.u_health.fragmentos

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.u_health.FragmentIMC
import com.example.u_health.R
import com.example.u_health.databinding.ActivityFrequencyBinding
import com.example.u_health.databinding.VistaFrecuenciaBinding
import com.example.u_health.databinding.VistaFrecuenciaDosisBinding

class Frequency : AppCompatActivity() {
    private lateinit var popupWindow: PopupWindow
    private lateinit var bindingF : ActivityFrequencyBinding
    private lateinit var bindingVF : VistaFrecuenciaBinding
    private lateinit var bindingVFD : VistaFrecuenciaDosisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingF = ActivityFrequencyBinding.inflate(layoutInflater)
        bindingVF = VistaFrecuenciaBinding.inflate(layoutInflater)
        bindingVFD = VistaFrecuenciaDosisBinding.inflate(layoutInflater)
        setContentView(bindingF.root)
        val valor = intent.getStringExtra("selectedItem")
        if (valor != null) {
            bindingF.label.text = valor
            initNumberPicker()
            valida()
        }
        bindingF.btnSiguiente.setOnClickListener {

        }
    }

    private fun valida() {
        actividadTwoNumberPicker(
            { showWindowFloat(0.89,bindingVF.root) },
            bindingVF.numberPickerHora,
            bindingVF.numberPickerMinutos,
            bindingVF.textviewPies,
            bindingVF.textviewPulgadas,
            bindingF.TextViewActividad,
            bindingVF.alturaVista
        )
        dosis()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun dosis(){
        bindingF.TextViewActividadDosis.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val touchX = event.x.toInt() // Posición X del evento táctil
                val imageWidth = bindingF.TextViewActividadDosis.compoundDrawablesRelative[2]?.bounds?.width() ?: 0 // Ancho de la imagen

                // Ajustar las coordenadas para la nueva posición del TextView
                val textViewEndPosition = bindingF.TextViewActividadDosis.width
                val touchThreshold = textViewEndPosition - imageWidth

                // Verificar si el evento táctil ocurrió dentro de la región de la imagen
                if (touchX >= bindingF.TextViewActividadDosis.width - touchThreshold) {
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
    private fun actividadTwoNumberPicker(mtdPeso: () -> Unit, numberPicker1: NumberPicker, numberPicker2: NumberPicker,
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
            codeClick(pesoVista,event,mtdPeso,TextViewActividadPeso)
        }
    }
    private fun codeClick(vista: ConstraintLayout, event: MotionEvent,
                          mtd: () -> Unit, TextViewActividad: TextView
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
                mtd()
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
}