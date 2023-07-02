package com.example.u_health

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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

        val Pastilla= intent.getStringExtra("Pastilla")
        val Tipo= intent.getStringExtra("Tipo")

        val Informacion=findViewById<TextView>(R.id.txtInfo)
        val usos=findViewById<TextView>(R.id.txtUsosContent)
        val Riesgos=findViewById<TextView>(R.id.txtRiesgos)


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