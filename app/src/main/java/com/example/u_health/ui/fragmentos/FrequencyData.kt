package com.example.u_health.ui.fragmentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.u_health.R
import com.example.u_health.databinding.ActivityFrequencyDataBinding

class FrequencyData : AppCompatActivity() {
    private lateinit var bindingFD :ActivityFrequencyDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frequency_data)
        bindingFD = ActivityFrequencyDataBinding.inflate(layoutInflater)
        setContentView(bindingFD.root)
        val valor = intent.getStringExtra("selectedItem")
        if (valor != null) {
            bindingFD.medicamentoSelect.text = valor
        }
        bindingFD.btnSiguiente.setOnClickListener {
            val intent = Intent(this, Frequency::class.java)
            intent.putExtra("selectedItem", valor)
            startActivity(intent)
        }
    }
}