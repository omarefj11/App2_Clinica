package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.adaptador.MedicinaAdapter
import com.example.appcolegio.controlador.ArregloMedicina
import com.example.appcolegio.entidad.Medicina

class MedicinaActivity : AppCompatActivity() {

    private lateinit var rvMedicinas: RecyclerView
    private lateinit var btnNuevaMedicina: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicina)

        rvMedicinas = findViewById(R.id.rvMedicina)
        btnNuevaMedicina = findViewById(R.id.btnNuevoMedicina)

        btnNuevaMedicina.setOnClickListener { nuevaMedicina() }

        // Inicializar RecyclerView y adaptador
        val adaptadorMedicina = MedicinaAdapter(ArregloMedicina().listado())
        rvMedicinas.adapter = adaptadorMedicina
        rvMedicinas.layoutManager = LinearLayoutManager(this)
    }

    private fun nuevaMedicina() {
        val intent = Intent(this, NuevaMedicina::class.java)
        startActivity(intent)
    }
}
