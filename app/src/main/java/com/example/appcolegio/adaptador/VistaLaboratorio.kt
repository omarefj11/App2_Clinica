package com.example.appcolegio.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.R
import com.example.appcolegio.entidad.Laboratorio

class VistaLaboratorio(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvNombreLaboratorio: TextView
    val tvDescripcion: TextView
    val tvTelefono: TextView

    init {
        // Inicializar los TextViews correctamente
        tvNombreLaboratorio = itemView.findViewById(R.id.tvNombreLaboratorio1)
        tvDescripcion = itemView.findViewById(R.id.tvDescripcion1)
        tvTelefono = itemView.findViewById(R.id.tvTelefono1)
    }
}
