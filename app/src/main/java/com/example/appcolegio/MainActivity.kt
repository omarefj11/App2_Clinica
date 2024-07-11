package com.example.appcolegio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.adaptador.DocenteAdapter

import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainActivity : AppCompatActivity() {
    private lateinit var rvDocentes: RecyclerView
    private lateinit var btnNuevoDocente: Button

    //declarar un obj que referencie a la bd de firebase
    private lateinit var BD: DatabaseReference

    var datos=ArrayList<Docente>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDocentes = findViewById(R.id.rvDocentes)
        btnNuevoDocente = findViewById(R.id.btnNuevo)

        btnNuevoDocente.setOnClickListener { nuevoDocente() }

        conectar()
        listado()
    }

    fun listado(){
        BD.child("docente").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(tabla: DataSnapshot) {
                tabla.children.forEach{row->
                    val doc=Docente(row.child("nombre").getValue<String>() as String,
                        row.child("paterno").getValue<String>() as String,
                        row.child("materno").getValue<String>() as String,
                        row.child("sueldo").getValue<Double>() as Double,
                        row.child("hijos").getValue<Int>() as Int,
                        row.child("id").getValue<String>() as String)
                    datos.add(doc)
                }

                val adaptador=DocenteAdapter(datos)
                rvDocentes.adapter = adaptador
                rvDocentes.layoutManager = LinearLayoutManager(appConfig.CONTEXT)
            }

            override fun onCancelled(p0: DatabaseError) {

                Toast.makeText(appConfig.CONTEXT,"No existe tabla docente",Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun nuevoDocente() {
        val intent = Intent(this, NuevoActivity::class.java)
        startActivity(intent)
    }

    //funcion para conectar a la bd firebase
    fun conectar(){
        //iniciar firebase en la clase actual
        FirebaseApp.initializeApp(appConfig.CONTEXT)
        //crear el obj bd
        BD= FirebaseDatabase.getInstance().reference
    }
}
