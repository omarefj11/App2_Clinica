package com.example.appcolegio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.adaptador.DocenteAdapter
import com.example.appcolegio.adaptador.LaboratorioAdapter
import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.entidad.Laboratorio
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class LaboratorioActivity : AppCompatActivity() {
    private lateinit var rvLaboratorios: RecyclerView
    private lateinit var btnNuevoLaboratorio: Button

    private lateinit var BD: DatabaseReference
    private var datosLab = ArrayList<Laboratorio>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laboratorio)

        rvLaboratorios = findViewById(R.id.rvLaboratorios)
        btnNuevoLaboratorio = findViewById(R.id.btnNuevoLaboratorio)

        btnNuevoLaboratorio.setOnClickListener { nuevoLaboratorio() }

        conectarFirebase()
        cargarLaboratorios()
    }

    private fun cargarLaboratorios() {
        BD.child("laboratorios").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(tabla: DataSnapshot) {
                tabla.children.forEach{row->
                    val lab= Laboratorio(row.child("nombreLaboratorio").getValue<String>() as String,
                        row.child("direccion").getValue<String>() as String,
                        row.child("telefono").getValue<String>() as String,
                        row.child("idLaboratorio").getValue<String>() as String)
                    datosLab.add(lab)
                }

                val adaptador= LaboratorioAdapter(datosLab)
                rvLaboratorios.adapter = adaptador
                rvLaboratorios.layoutManager = LinearLayoutManager(appConfig.CONTEXT)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(appConfig.CONTEXT, "Error al cargar laboratorios", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun nuevoLaboratorio() {
        val intent = Intent(this, NuevoLaboratorio::class.java)
        startActivity(intent)
    }

    private fun conectarFirebase() {
        FirebaseApp.initializeApp(this)
        BD = FirebaseDatabase.getInstance().reference
    }
}
