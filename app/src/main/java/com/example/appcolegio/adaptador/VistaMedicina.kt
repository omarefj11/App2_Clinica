package com.example.appcolegio.adaptador

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.R

class VistaMedicina(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvIdMedicina:TextView
    var tvNombreM: TextView
    var tvDescripcion: TextView
    var tvPrecio: TextView

    init {
        // Referencia a los elementos de diseño
        tvIdMedicina=itemView.findViewById(R.id.tvIdMedicina)
        tvNombreM = itemView.findViewById(R.id.tvNombreMedicina)
        tvDescripcion = itemView.findViewById(R.id.tvDescripcion)
        tvPrecio = itemView.findViewById(R.id.tvPrecio)
    }
}
