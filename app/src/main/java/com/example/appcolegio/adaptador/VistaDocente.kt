package com.example.appcolegio.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.R

class VistaDocente(itemView: View):RecyclerView.ViewHolder(itemView) {

    var tvNombre:TextView
    var tvPaterno:TextView
    var tvMaterno:TextView

    init{
        //referencia

        tvNombre=itemView.findViewById(R.id.tvNombre)
        tvPaterno=itemView.findViewById(R.id.tvApellido)
        tvMaterno=itemView.findViewById(R.id.tvEdad)
    }


}