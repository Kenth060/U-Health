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

        entrada_de_datos()


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

    private fun entrada_de_datos() {
        val sharedPref = getSharedPreferences("MiSharedPreferences", Context.MODE_PRIVATE)

        bindingCrearPerfil.TextViewActividadGenero.hint = sharedPref.getString("genero", "")
        bindingCrearPerfil.TextViewActividadEnfermedad.hint = sharedPref.getString("enfermedad", "")
        bindingCrearPerfil.TextViewActividadPeso.hint = sharedPref.getString("peso", "")
        bindingCrearPerfil.TextViewActividadAltura.hint = sharedPref.getString("altura", "")
        bindingCrearPerfil.TextViewActividadFechaNac.hint = sharedPref.getString("edad", "")

        Toast.makeText(this, bindingCrearPerfil.TextViewActividadGenero.hint,
            Toast.LENGTH_SHORT).show()
        Toast.makeText(this, bindingCrearPerfil.TextViewActividadEnfermedad.hint,
            Toast.LENGTH_SHORT).show()

        Toast.makeText(this, bindingCrearPerfil.TextViewActividadPeso.hint,
            Toast.LENGTH_SHORT).show()
        Toast.makeText(this, bindingCrearPerfil.TextViewActividadAltura.hint,
            Toast.LENGTH_SHORT).show()
        Toast.makeText(this, bindingCrearPerfil.TextViewActividadFechaNac.hint,
            Toast.LENGTH_SHORT).show()

        //limpiando los datos que tenga almacenado sharedPreference
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    private fun reemplazo(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaccion = fragmentManager.beginTransaction()
        fragmentTransaccion.replace(R.id.fragment_layout,fragment)
        fragmentTransaccion.commit()
    }
}