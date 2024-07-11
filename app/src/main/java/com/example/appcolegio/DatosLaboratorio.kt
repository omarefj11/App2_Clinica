package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.entidad.Laboratorio
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatosLaboratorio : AppCompatActivity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnVolver: Button

    private lateinit var laboratorio: Laboratorio
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_laboratorio)

        edtNombre = findViewById(R.id.edtNombreLaboratorio)
        edtDescripcion = findViewById(R.id.edtDescripcionLaboratorio)
        edtTelefono = findViewById(R.id.edtTelefono1)
        btnActualizar = findViewById(R.id.btnActualizarLaboratorio)
        btnEliminar = findViewById(R.id.btnEliminarLaboratorio)
        btnVolver = findViewById(R.id.btnVolverLaboratorio)

        btnEliminar.setOnClickListener { eliminarLaboratorio() }
        btnActualizar.setOnClickListener { actualizarLaboratorio() }
        btnVolver.setOnClickListener { volver() }

        verDatos()
        conectarFirebase()
    }

    private fun eliminarLaboratorio() {
        database.child("laboratorios").child(laboratorio.idLaboratorio).removeValue()
            .addOnCompleteListener {
                Toast.makeText(this, "Laboratorio eliminado", Toast.LENGTH_LONG).show()
                volver()
            }
    }

    private fun actualizarLaboratorio() {
        var nombre = edtNombre.text.toString()
        var descripcion = edtDescripcion.text.toString()
        var telefono = edtTelefono.text.toString()

        laboratorio.nombreLaboratorio = nombre
        laboratorio.direccion = descripcion
        laboratorio.telefono = telefono

        database.child("laboratorios").child(laboratorio.idLaboratorio).setValue(laboratorio)
            .addOnCompleteListener {
                Toast.makeText(this, "Laboratorio actualizado", Toast.LENGTH_LONG).show()
            }
        Toast.makeText(this,"Laboratorio registrado",Toast.LENGTH_LONG).show()
    }

    private fun volver() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun verDatos() {
        laboratorio = intent.getSerializableExtra("data") as Laboratorio

        edtNombre.setText(laboratorio.nombreLaboratorio)
        edtDescripcion.setText(laboratorio.direccion)
        edtTelefono.setText(laboratorio.telefono)
    }

    private fun conectarFirebase() {
        FirebaseApp.initializeApp(appConfig.CONTEXT)
        database = FirebaseDatabase.getInstance().reference
    }
}
