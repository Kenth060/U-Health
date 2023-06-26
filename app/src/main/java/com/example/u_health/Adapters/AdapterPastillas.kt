package com.example.u_health.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.u_health.R


class AdapterPastillas (private val pastillasList:List<String>): RecyclerView.Adapter<PastillasViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastillasViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)

        return  PastillasViewHolder(layoutInflater.inflate(R.layout.item_pastillas, parent, false))
    }

    override fun onBindViewHolder(holder: PastillasViewHolder, position: Int)
    {
        val item=pastillasList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = pastillasList.size


}




