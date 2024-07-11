package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.controlador.ArregloPaciente
import com.example.appcolegio.entidad.Paciente

class DatosPaciente: AppCompatActivity() {
    private lateinit var edtId: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtEdad: EditText
    private lateinit var edtGenero: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_pacientes)

        edtId = findViewById(R.id.edtIdMedicina)
        edtNombre = findViewById(R.id.edtNombreA)
        edtApellido = findViewById(R.id.edtApellidoA)
        edtEdad = findViewById(R.id.edtEdadA)
        edtGenero = findViewById(R.id.edtGeneroA)
        edtTelefono = findViewById(R.id.edtTelefonoA)
        btnActualizar = findViewById(R.id.btnActualizarPaciente)
        btnEliminar = findViewById(R.id.btnEliminarPaciente)
        btnVolver = findViewById(R.id.btnVolverPaciente)

        btnEliminar.setOnClickListener { eliminarPaciente() }
        btnActualizar.setOnClickListener { actualizarPaciente() }
        btnVolver.setOnClickListener { volver() }

        verDatos()
    }

    private fun eliminarPaciente() {
        val id = edtId.text.toString().toInt()
        ArregloPaciente().deletePaciente(id)
        Toast.makeText(this, "Paciente eliminado", Toast.LENGTH_LONG).show()
    }

    private fun actualizarPaciente() {
        val id = edtId.text.toString().toInt()
        val nombre = edtNombre.text.toString()
        val apellido = edtApellido.text.toString()
        val edad = edtEdad.text.toString().toInt()
        val genero = edtGenero.text.toString()
        val telefono = edtTelefono.text.toString()

        val paciente = Paciente(id, nombre, apellido, edad, genero, telefono)
        ArregloPaciente().updatePaciente(paciente)
        Toast.makeText(this, "Paciente actualizado", Toast.LENGTH_LONG).show()
    }

    private fun volver() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun verDatos() {
        val paciente = intent.getSerializableExtra("data") as Paciente
        edtId.setText(paciente.id.toString())
        edtNombre.setText(paciente.nombre)
        edtApellido.setText(paciente.apellido)
        edtEdad.setText(paciente.edad.toString())
        edtGenero.setText(paciente.genero)
        edtTelefono.setText(paciente.telefono)
    }
}
