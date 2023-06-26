package com.example.u_health

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.u_health.Adapters.AdapterPastillas
import com.example.u_health.Adapters.MedicamentosProvider
import com.example.u_health.Adapters.PastillasViewHolder

class informacion_medicamentos : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_medicamentos)
        initRecyclerView()
    }

    fun initRecyclerView()
    {
        val rv= findViewById<RecyclerView>(R.id.rvPastillas)
        rv.layoutManager=LinearLayoutManager(this)
        rv.adapter=AdapterPastillas(MedicamentosProvider.Antibioticos)
    }
}