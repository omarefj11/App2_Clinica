package com.example.appcolegio.adaptador
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.DatosActivity
import com.example.appcolegio.R
import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.utils.appConfig

class DocenteAdapter(val info:ArrayList<Docente>)
    :RecyclerView.Adapter<VistaDocente>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaDocente {
        val vista=LayoutInflater.from(parent.context).inflate(R.layout.item_docente,parent,false)
        return VistaDocente(vista)
    }
    override fun getItemCount(): Int {
       return info.size
    }
    override fun onBindViewHolder(holder: VistaDocente, position: Int) {

        holder.tvNombre.text=info.get(position).nombre
        holder.tvPaterno.text=info.get(position).paterno
        holder.tvMaterno.text=info.get(position).materno

        //evento clic a holder
        holder.itemView.setOnClickListener {
            var pantalla=Intent(appConfig.CONTEXT,DatosActivity::class.java)
            //enviar clave dentro de panllata con el valor del docente actual
            pantalla.putExtra("data",info.get(position))
            pantalla.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ContextCompat.startActivity(appConfig.CONTEXT, pantalla,null)
        }

    }
}

