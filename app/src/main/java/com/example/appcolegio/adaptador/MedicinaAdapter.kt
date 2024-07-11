package com.example.appcolegio.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.DatosActivity
import com.example.appcolegio.DatosMedicinas
import com.example.appcolegio.R
import com.example.appcolegio.entidad.Medicina
import com.example.appcolegio.utils.appConfig

class MedicinaAdapter(val info: ArrayList<Medicina>) :
    RecyclerView.Adapter<VistaMedicina>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaMedicina {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_medicina, parent, false)
        return VistaMedicina(vista)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: VistaMedicina, position: Int) {
        val medicina = info[position]
        holder.tvIdMedicina.text=info[position].idMedicina.toString()
        holder.tvNombreM.text = medicina.nombreM
        holder.tvDescripcion.text = medicina.descripcion
        holder.tvPrecio.text = medicina.precio.toString()

        // Evento de clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener {
            val pantalla = Intent(appConfig.CONTEXT, DatosMedicinas::class.java)
            pantalla.putExtra("data", medicina)
            pantalla.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXT, pantalla, null)
        }
    }
}
