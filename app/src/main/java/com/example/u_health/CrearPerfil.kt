package com.example.u_health

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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.diseodatos.DatePickerFragment
import com.example.u_health.databinding.ActivityCrearPerfilBinding
import com.example.u_health.databinding.VistaAlturaBinding
import com.example.u_health.databinding.VistaEnfermedadBinding
import com.example.u_health.databinding.VistaGeneroBinding
import com.example.u_health.databinding.VistaPesoBinding
import java.util.Calendar

class CrearPerfil : AppCompatActivity()
{
    private lateinit var popupWindow: PopupWindow
    private lateinit var binding : ActivityCrearPerfilBinding
    private lateinit var bindingGenero : VistaGeneroBinding
    private lateinit var bindingPeso : VistaPesoBinding
    private lateinit var bindingAltura : VistaAlturaBinding
    private lateinit var bindingEnfermedad : VistaEnfermedadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearPerfilBinding.inflate(layoutInflater)
        bindingGenero = VistaGeneroBinding.inflate(layoutInflater)
        bindingPeso = VistaPesoBinding.inflate(layoutInflater)
        bindingAltura = VistaAlturaBinding.inflate(layoutInflater)
        bindingEnfermedad = VistaEnfermedadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSiguiente.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        }


        initNumberPicker()
        activityCalendarFN()
        //metodo para ventanas flotantes que contiene un solo numberPicker
        activityNumberPickerOne(
            {showWindowFloat(0.8,bindingGenero.root)},
            bindingGenero.numberPicker,
            binding.TextViewActividadGenero,
            bindingGenero.generoVista,
            bindingGenero.textViewSelect
        )
        activityNumberPickerOne(
            {showWindowFloat(0.8,bindingEnfermedad.root)},
            bindingEnfermedad.numberPickerEnfermedad,
            binding.TextViewActividadEnfermedad,
            bindingEnfermedad.enfermedadVista,
            bindingEnfermedad.textViewSelectEnfermedad
        )

        //metodos para ventenas flotantes que contienen dos numberpicker
        actividadTwoNumberPicker(
            { showWindowFloat(0.89,bindingPeso.root) },
            bindingPeso.numberPicker1,
            bindingPeso.numberPicker2,
            bindingPeso.textview2,
            bindingPeso.textview3,
            binding.TextViewActividadPeso,
            bindingPeso.pesoVista
        )
        actividadTwoNumberPicker(
            { showWindowFloat(0.89,bindingAltura.root) },
            bindingAltura.numberPickerPies,
            bindingAltura.numberPickerPulgadas,
            bindingAltura.textviewPies,
            bindingAltura.textviewPulgadas,
            binding.TextViewActividadAltura,
            bindingAltura.alturaVista
        )
        actividadCancelar()
        //updateTextView()
    }
    private fun initNumberPicker() {
        //genero
        bindingGenero.numberPicker.minValue = 0
        bindingGenero.numberPicker.maxValue = 3
        bindingGenero.numberPicker.displayedValues = arrayOf(
            "Masculino","Femenino","Prefiero no decir","Otro"
        )
        //peso
        bindingPeso.numberPicker1.minValue = 80
        bindingPeso.numberPicker1.maxValue = 300
        bindingPeso.numberPicker2.minValue = 0
        bindingPeso.numberPicker2.maxValue = 9

        //altura
        bindingAltura.numberPickerPies.minValue = 0
        bindingAltura.numberPickerPies.maxValue = 9
        bindingAltura.numberPickerPulgadas.minValue = 0
        bindingAltura.numberPickerPulgadas.maxValue = 9

        bindingAltura.numberPickerPies.displayedValues = Array(10) { "$it ft" }
        bindingAltura.numberPickerPulgadas.displayedValues = Array(10) { "$it in" }

        //enfermedad
        bindingEnfermedad.numberPickerEnfermedad.minValue = 0
        bindingEnfermedad.numberPickerEnfermedad.maxValue = 1
        bindingEnfermedad.numberPickerEnfermedad.displayedValues = arrayOf(
            "Diabetes","Presion"
        )
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun showWindowFloat(width: Double, popupView: View) {
        val widthPixels = (resources.displayMetrics.widthPixels * width).toInt()
        val heightPixels = (resources.displayMetrics.heightPixels * 0.4).toInt()
        //condicion para verificar si el popupView tiene una vsta asignada
        if (popupView.parent != null) {
            (popupView.parent as ViewGroup).removeView(popupView)
        }
        popupWindow = PopupWindow(popupView, widthPixels, heightPixels, false) //Evita cerrarse cuando clickeas fuera del rango de la ventana
        popupWindow.showAtLocation(popupView.rootView, Gravity.CENTER, 0, 0)
    }


    private fun actividadCancelar(){
        //diseño del genero
        bindingGenero.iconAtras.setOnClickListener {
            popupWindow.dismiss()
        }

        bindingGenero.btnCancelar.setOnClickListener {
            popupWindow.dismiss()
        }

        //diseño del peso
        bindingPeso.iconAtrasPeso.setOnClickListener {
            popupWindow.dismiss()
        }
        bindingPeso.btnCancelarpeso.setOnClickListener {
            popupWindow.dismiss()
        }

        //diseño de altura
        bindingAltura.iconAtrasAltura.setOnClickListener {
            popupWindow.dismiss()
        }
        bindingAltura.btnCancelarAltura.setOnClickListener {
            popupWindow.dismiss()
        }

        //diseño de enfermedad
        bindingEnfermedad.iconAtrasEnfermedad.setOnClickListener {
            popupWindow.dismiss()
        }
        bindingEnfermedad.btnCancelarEnfermedad.setOnClickListener {
            popupWindow.dismiss()
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


    @SuppressLint("ClickableViewAccessibility")
    private fun activityNumberPickerOne(mtdShow : () -> Unit, numberPicker : NumberPicker,
                                        TextViewActividad: TextView, vista : ConstraintLayout,
                                        selectTextView: TextView
    ) {
        numberPicker.setOnValueChangedListener { _, _, _ ->
            updateTextView(numberPicker,selectTextView)
        }
        TextViewActividad.setOnTouchListener { _, event ->
            codeClick(
                vista,
                event,
                mtdShow,
                TextViewActividad
            )
        }
        actividadCancelar()
        activityAccept()
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
    @SuppressLint("ClickableViewAccessibility")
    private fun activityCalendarFN(){
        binding.TextViewActividadFechaNac.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val touchX = event.x.toInt() // Posición X del evento táctil
                val imageWidth =
                    binding.TextViewActividadFechaNac.compoundDrawablesRelative[2]?.bounds?.width()
                        ?: 0 // Ancho de la imagen

                // Ajustar las coordenadas para la nueva posición del TextView
                val textViewEndPosition = binding.TextViewActividadFechaNac.width
                val touchThreshold = textViewEndPosition - imageWidth

                // Verificar si el evento táctil ocurrió dentro de la región de la imagen
                if (touchX >= binding.TextViewActividadFechaNac.width - touchThreshold) {
                    showDatePickerDialog()
                }
            }
            false
        }
    }

    private fun activityAccept() {
        //diseño de genero
        bindingGenero.btnAceptar.setOnClickListener {
            confirmationDialogAccept(bindingGenero.textViewSelect,bindingGenero.numberPicker,
                bindingGenero.generoVista, binding.TextViewActividadGenero)
        }
        //diseño de peso
        bindingPeso.btnAceptarpeso.setOnClickListener {
            confirmationDialogAcceptWeight()
        }
        //diseño de altura
        bindingAltura.btnAceptarAltura.setOnClickListener {
            confirmacionDialogAceptarAltura()
        }
        bindingEnfermedad.btnAceptarEnfermedad.setOnClickListener {
            confirmationDialogAccept(bindingEnfermedad.textViewSelectEnfermedad,bindingEnfermedad.numberPickerEnfermedad,
                bindingEnfermedad.enfermedadVista, binding.TextViewActividadEnfermedad)
        }

    }
    private fun confirmationDialogAccept(textViewSelect : TextView, numberPicker : NumberPicker,
                                         vista : ConstraintLayout, TextViewActividad : TextView
    ) {
        val dialogBuilder = AlertDialog.Builder(this, R.style.ConfirmationDialog)
        dialogBuilder.setTitle("Confirmar")
            .setMessage("¿Desea confirmar su genero ${textViewSelect.text}?")
            .setPositiveButton("Sí") { dialog, _ ->
                dialog.dismiss()
                val selectedValue = numberPicker.value
                TextViewActividad.hint = numberPicker.displayedValues[selectedValue]
                //vista.visibility = View.GONE
                popupWindow.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
    private fun confirmationDialogAcceptWeight() {
        if(bindingPeso.textview2.text.equals("Peso") && bindingPeso.textview3.text.equals("Peso")){
            dialog(bindingPeso.numberPicker1,bindingPeso.numberPicker2,binding.TextViewActividadPeso,
                bindingPeso.pesoVista)
        }else{
            dialog(bindingPeso.numberPicker1,bindingPeso.numberPicker2,binding.TextViewActividadPeso,
                bindingPeso.pesoVista)
        }

    }
    private fun dialog(numberPicker1: NumberPicker, numberPicker2: NumberPicker,
                       TextViewActividad: TextView, vista: ConstraintLayout
    ) {
        val selectedValue1 = numberPicker1.value
        val selectedValue2 = numberPicker2.value
        val dialogBuilder = AlertDialog.Builder(this, R.style.ConfirmationDialog)
        dialogBuilder.setTitle("Confirmar")
            .setMessage("¿Desea confirmar su medida ${selectedValue1}.${selectedValue2}?")
            .setPositiveButton("Sí") { dialog, _ ->
                dialog.dismiss()
                TextViewActividad.hint = "$selectedValue1.$selectedValue2"
                //vista.visibility = View.GONE
                popupWindow.dismiss()
            }.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }.create().show()
    }
    private fun confirmacionDialogAceptarAltura() {
        if((bindingAltura.textviewPies.text.isEmpty() || bindingAltura.textviewPulgadas.text.isEmpty())||
            bindingAltura.textviewPies.text.equals("0") && bindingAltura.textviewPulgadas.text.equals("0")){
            val dialogBuilder = AlertDialog.Builder(this, R.style.ConfirmationDialog)
            dialogBuilder.setTitle("Recomendacion")
                .setMessage("Seleccione una medida de altura")
                .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }.create().show()
        }else{
            dialog(bindingAltura.numberPickerPies,bindingAltura.numberPickerPulgadas,
                binding.TextViewActividadAltura,bindingAltura.alturaVista)
        }

    }
    private fun updateTextView(numberPicker : NumberPicker, textViewSelect : TextView) {
        val selectedValue = numberPicker.value
        textViewSelect.text = numberPicker.displayedValues[selectedValue]
    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { dia, mes, year -> currentDate(dia, mes, year) }
        datePicker.show(supportFragmentManager, "datepicker")
    }
    private fun currentDate(dia: Int, mes: Int, yearNac: Int) {
        val yearActual = Calendar.getInstance().get(Calendar.YEAR)
        val monthActual = Calendar.getInstance().get(Calendar.MONTH)
        val dayActual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        var edad = yearActual - yearNac

        if (monthActual < mes || (monthActual == mes && dayActual < dia)) edad--
        binding.TextViewActividadFechaNac.text = "$edad"
    }
}