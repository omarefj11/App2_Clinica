package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.entidad.Laboratorio
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NuevoLaboratorio : AppCompatActivity() {
    private lateinit var edtNombre: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtTelefono: EditText
    private lateinit var btnGrabar: Button
    private lateinit var btnVolver: Button

    // Referencia a la base de datos de Firebase
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_laboratorio)

        edtNombre = findViewById(R.id.edtNombreLaboratorio)
        edtDescripcion = findViewById(R.id.edtDescripcionLaboratorio)
        edtTelefono = findViewById(R.id.edtTelefonoLaboratorio)
        btnGrabar = findViewById(R.id.btnGuardarLaboratorio)
        btnVolver = findViewById(R.id.btnVolverLaboratorio)

        btnGrabar.setOnClickListener { grabarLaboratorio() }
        btnVolver.setOnClickListener { volver() }

        conectarFirebase()
    }

    fun grabarLaboratorio() {
        // Generar una clave única para el nuevo laboratorio
        val key = database.push().key

        // Leer los valores de las cajas de texto
        var nom = edtNombre.text.toString()
        var des = edtDescripcion.text.toString()
        var tel = edtTelefono.text.toString()

        var lab = Laboratorio(nom, des, tel, key!!)

        database.child("Laboratorio").child(key!!).setValue(lab).addOnCompleteListener {
            Toast.makeText(this, "Laboratorio Registrado", Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this, "Laboratorio registrado", Toast.LENGTH_LONG).show()
    }

    fun volver() {
        val intent = Intent(this,LaboratorioActivity::class.java)
        startActivity(intent)
    }

    fun conectarFirebase() {
        // Inicializar Firebase en la aplicación
        FirebaseApp.initializeApp(this)
        // Obtener referencia a la base de datos
        database = FirebaseDatabase.getInstance().reference
    }
}
