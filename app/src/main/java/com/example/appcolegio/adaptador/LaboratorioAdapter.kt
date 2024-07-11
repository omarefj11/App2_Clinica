package com.example.appcolegio.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.DatosActivity
import com.example.appcolegio.DatosLaboratorio
import com.example.appcolegio.LaboratorioActivity
import com.example.appcolegio.R
import com.example.appcolegio.entidad.Laboratorio
import com.example.appcolegio.utils.appConfig

class LaboratorioAdapter(val info: ArrayList<Laboratorio>) :
    RecyclerView.Adapter<VistaLaboratorio>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaLaboratorio {
        // Inflar el diseño del elemento del laboratorio
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_laboratorio, parent, false)
        return VistaLaboratorio(vista)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: VistaLaboratorio, position: Int) {
        // Asignar los datos del laboratorio al ViewHolder
        holder.tvNombreLaboratorio.text = info.get(position).nombreLaboratorio
        holder.tvDescripcion.text = info.get(position).direccion
        holder.tvTelefono.text = info.get(position).telefono

        // Evento de clic en el elemento del RecyclerView
        holder.itemView.setOnClickListener {
            val pantalla = Intent(appConfig.CONTEXT, DatosLaboratorio::class.java)
            pantalla.putExtra("data", info[position])
            pantalla.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXT, pantalla, null)
        }
    }
}
