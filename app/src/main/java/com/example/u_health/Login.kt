package com.example.u_health

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.u_health.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            if (validarUsuario() == true) {
                firebaseAuth.signInWithEmailAndPassword(
                    binding.txtCorreo.text.toString(),
                    binding.txtContraseA.text.toString()
                ).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        binding.txtCorreo.setText("")
                        binding.txtContraseA.setText("")
                    } else {
                        Toast.makeText(this, "El Usuario y Clave no existen.", Toast.LENGTH_SHORT)
                            .show();
                    }
                })
            }
        }

        binding.btnSignIn.setOnClickListener { v->
            val intent = Intent(v.context, CrearCuenta::class.java)
            startActivity(intent)
        }
    }

    fun validarUsuario():Boolean{
        try {
            var validaok:Boolean=false
            if(binding.txtCorreo.text?.length?.equals(0)!!){
                binding.txtCorreo.requestFocus()
                binding.txtCorreo.setError("Debe ingresar su correo electronico.")
                return validaok
            }
            if(binding.txtContraseA.text?.length?.equals(0)!!){
                binding.txtContraseA.requestFocus()
                binding.txtContraseA.setError("Debe ingresar una contraseña.")
                return validaok
            }
            validaok=true
            return validaok
        }catch (e:Exception){
            e.message?.let{ Log.e("Error en validacion",it)};
            return false;
        }
    }

}