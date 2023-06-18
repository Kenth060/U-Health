package com.example.u_health

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.u_health.databinding.ActivityCrearPerfilBinding
import com.example.u_health.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var bindingCrearPerfil : ActivityCrearPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingCrearPerfil = ActivityCrearPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reemplazo(Fragment_today())
        binding.BottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btnHoy -> reemplazo(Fragment_today())
                R.id.btnProgreso -> reemplazo(Fragment_bars())
                R.id.btnSoporte -> reemplazo(Fragment_soporte())
                R.id.btnTerapia -> reemplazo(Fragment_terapia())
                R.id.btnRecordatorios -> reemplazo(Fragment_recordatorios())
                else -> {

                }
            }
            true
        }

    }


    private fun reemplazo(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaccion = fragmentManager.beginTransaction()
        fragmentTransaccion.replace(R.id.fragment_layout,fragment)
        fragmentTransaccion.commit()
    }
}