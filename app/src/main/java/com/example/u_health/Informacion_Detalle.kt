package com.example.u_health

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.u_health.databinding.ActivityInformacionDetalleBinding
import com.example.u_health.databinding.FragmentUsuarioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Informacion_Detalle : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_detalle)


        val toolbar: Toolbar = findViewById<Toolbar>(R.id.tbInformacionDetalles)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = getString(R.string.Informacion_Detalle)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val Pastilla= intent.getStringExtra("Pastilla")
        val Tipo= intent.getStringExtra("Tipo")

        val Nombre_Pastilla=findViewById<TextView>(R.id.txtNombrePastilla)
        val Informacion=findViewById<TextView>(R.id.txtInfo)
        val usos=findViewById<TextView>(R.id.txtUsosContent)
        val Riesgos=findViewById<TextView>(R.id.txtRiesgos)

        Nombre_Pastilla.text=Pastilla
        val fireDB: FirebaseFirestore = FirebaseFirestore.getInstance()
        val auth : FirebaseAuth = FirebaseAuth.getInstance()

        if(Tipo!=null && Pastilla!= null)
        {
            val pills = fireDB.collection(Tipo).document(Pastilla)

            pills.get().addOnSuccessListener{
                Informacion.text= it.get("Informacion").toString()
                Riesgos.text= it.get("Riesgos").toString()
                usos.text= it.get("Usos").toString()
            }
        }

    }
}