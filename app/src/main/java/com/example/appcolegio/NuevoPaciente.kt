package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.controlador.ArregloPaciente
import com.example.appcolegio.entidad.Paciente

class NuevoPaciente : AppCompatActivity() {
    private lateinit var edtNombre: EditText
    private lateinit var edtApellido: EditText
    private lateinit var edtEdad: EditText
    private lateinit var edtGenero: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var btnGrabarPaciente: Button
    private lateinit var btnVolver1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_paciente)
        edtNombre = findViewById(R.id.edtNombre)
        edtApellido = findViewById(R.id.edtApellido)
        edtEdad = findViewById(R.id.edtEdad)
        edtGenero = findViewById(R.id.edtGenero)
        edtTelefono = findViewById(R.id.edtTelefono)
        btnGrabarPaciente = findViewById(R.id.btnGrabarPaciente)
        btnVolver1 = findViewById(R.id.btnVolver1)

        btnGrabarPaciente.setOnClickListener { grabarPaciente() }
        btnVolver1.setOnClickListener { volver() }
    }

    private fun grabarPaciente() {
        // Leer datos de las cajas de texto
        val nombre = edtNombre.text.toString()
        val apellido = edtApellido.text.toString()
        val edad = edtEdad.text.toString().toInt()
        val genero = edtGenero.text.toString()
        val telefono = edtTelefono.text.toString()

        // Crear objeto Paciente con los datos ingresados
        val Pa = Paciente(0, nombre, apellido, edad, genero, telefono)

        // Guardar paciente usando el controlador ArregloPaciente
        ArregloPaciente().savePaciente(Pa)

        // Mostrar mensaje de confirmación
        Toast.makeText(this, "Paciente registrado", Toast.LENGTH_LONG).show()
    }

    private fun volver() {
        val intent = Intent(this, PacientesActivity::class.java)
        startActivity(intent)
    }
}
