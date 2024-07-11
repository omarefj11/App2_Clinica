package com.example.appcolegio.adaptador

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.DatosPaciente
import com.example.appcolegio.R
import com.example.appcolegio.entidad.Paciente
import com.example.appcolegio.utils.appConfig

class PacienteAdapter(val info: ArrayList<Paciente>)
    : RecyclerView.Adapter<VistaPaciente>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaPaciente {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_paciente, parent, false)
        return VistaPaciente(vista)
    }

    override fun getItemCount(): Int {
        return info.size
    }

    override fun onBindViewHolder(holder: VistaPaciente, position: Int) {
        holder.tvId.text = info[position].id.toString()
        holder.tvNombre.text = info[position].nombre
        holder.tvApellido.text = info[position].apellido
        holder.tvEdad.text = info[position].edad.toString()

        // Evento clic en el holder
        holder.itemView.setOnClickListener {
            val pantalla = Intent(appConfig.CONTEXT, DatosPaciente::class.java)
            // Enviar clave dentro de la pantalla con el valor del paciente actual
            pantalla.putExtra("data", info[position])
            pantalla.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            ContextCompat.startActivity(appConfig.CONTEXT, pantalla, null)
        }
    }
}
