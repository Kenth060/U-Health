package com.example.u_health

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.u_health.Adapters.AdapterPastillas
import com.example.u_health.Adapters.MedicamentosProvider
import com.example.u_health.Adapters.PastillasListener
import com.example.u_health.Adapters.PastillasViewHolder
import com.example.u_health.databinding.ActivityCrearCuentaBinding
import com.example.u_health.databinding.ActivityInformacionMedicamentosBinding
import com.example.u_health.databinding.FragmentVistaMedicamentoBinding
import javax.annotation.meta.When

class informacion_medicamentos : AppCompatActivity() , PastillasListener
{
        private lateinit var binding:ActivityInformacionMedicamentosBinding
        @SuppressLint("ResourceAsColor")
        override fun onCreate(savedInstanceState: Bundle?)
        {
            super.onCreate(savedInstanceState)

            binding = ActivityInformacionMedicamentosBinding.inflate(layoutInflater)
            val view= binding.root
            setContentView(view)

            val toolbar: Toolbar = findViewById<Toolbar>(R.id.tb_Info)
            toolbar.title = getString(R.string.Informacion_Detalle)
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.navigationIcon?.setTint(R.color.textologin)
            toolbar.setNavigationOnClickListener {
                Navigation.findNavController(view).navigate(R.id.navigation_informacion)
            }

            initRecyclerView()
        }

        fun initRecyclerView()
        {
            val opc= intent.getStringExtra("opc")

            val rv= findViewById<RecyclerView>(R.id.rvPastillas)
            rv.layoutManager=LinearLayoutManager(this)


            if(opc=="1")
            {rv.adapter=AdapterPastillas(MedicamentosProvider.Analgesicos,this)}
            else if (opc=="2")
            { rv.adapter=AdapterPastillas(MedicamentosProvider.Antiflamatorios,this) }
            else if (opc == "3")
            { rv.adapter=AdapterPastillas(MedicamentosProvider.Antibioticos,this) }
            else if (opc == "4")
            { rv.adapter=AdapterPastillas(MedicamentosProvider.Antidepresivos,this) }
            else if (opc == "5")
            { rv.adapter=AdapterPastillas(MedicamentosProvider.MedDiabetes,this) }
            else if (opc == "6")
            { rv.adapter=AdapterPastillas(MedicamentosProvider.MediTosGripe,this) }
            else
            { rv.adapter=AdapterPastillas(MedicamentosProvider.Prueba,this) }


        }
    override fun onPastillaClicked(p: String)
    {

        val opc= intent.getStringExtra("opc")

        if(opc=="1")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Analgesicos")}
            startActivity(intent)
        }
        else if (opc=="2")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Antiflamatorios")}
            startActivity(intent)
        }
        else if (opc == "3")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Antibioticos")}
            startActivity(intent)
        }
        else if (opc == "4")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Antidepresivos")}
            startActivity(intent)
        }
        else if (opc == "5")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Medicamentos para la diabetes")}
            startActivity(intent)
        }
        else if (opc == "6")
        {
            val intent = Intent(this , Informacion_Detalle::class.java).apply {
                putExtra("Pastilla",p)
                putExtra("Tipo","Medicamentos para la tos y gripe")}
            startActivity(intent)
        }




    }
}

