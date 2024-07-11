package com.example.appcolegio.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.R

class VistaPaciente(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvId: TextView
    var tvNombre: TextView
    var tvApellido: TextView
    var tvEdad: TextView

    init {
        // Referencia a los elementos de diseño
        tvId = itemView.findViewById(R.id.tvId)
        tvNombre = itemView.findViewById(R.id.tvNombre)
        tvApellido = itemView.findViewById(R.id.tvApellido)
        tvEdad = itemView.findViewById(R.id.tvEdad)
    }
}
