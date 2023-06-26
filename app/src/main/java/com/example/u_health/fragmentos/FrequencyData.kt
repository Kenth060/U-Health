package com.example.u_health.fragmentos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        bindingFD.btnGuardar.setOnClickListener {
            if(bindingFD.radioButton1.isChecked || bindingFD.radioButton2.isChecked || bindingFD.radioButton3.isChecked){
                val intent = Intent(this, Frequency::class.java)
                intent.putExtra("selectedItem", valor)
                startActivity(intent)

            }else{
                Toast.makeText(this, "Rellene los datos Juan dundo", Toast.LENGTH_SHORT).show()
            }

        }


    }
}