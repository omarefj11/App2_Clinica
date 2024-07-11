package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.adaptador.PacienteAdapter
import com.example.appcolegio.controlador.ArregloPaciente
import com.example.appcolegio.entidad.Paciente

class PacientesActivity : AppCompatActivity() {

    private lateinit var rvPacientes: RecyclerView
    private lateinit var btnNuevoPaciente: Button

    var datosPacientes = ArrayList<Paciente>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)

        rvPacientes = findViewById(R.id.rvPaciente)
        btnNuevoPaciente = findViewById(R.id.btnNuevo2)

        btnNuevoPaciente.setOnClickListener { nuevoPaciente() }

        // Adaptador y LayoutManager para Pacientes
        val adaptadorPaciente = PacienteAdapter(ArregloPaciente().listado())
        rvPacientes.adapter = adaptadorPaciente
        rvPacientes.layoutManager = LinearLayoutManager(this)
    }

    fun nuevoPaciente() {
        val intent = Intent(this, NuevoPaciente::class.java)
        startActivity(intent)
    }
}
