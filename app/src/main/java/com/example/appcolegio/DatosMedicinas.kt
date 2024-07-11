package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.controlador.ArregloMedicina
import com.example.appcolegio.entidad.Medicina

class DatosMedicinas : AppCompatActivity() {
    private lateinit var edtId: EditText
    private lateinit var edtNombre: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtPrecio: EditText
    private lateinit var edtFechaExpiracion: EditText
    private lateinit var edtProveedor: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_medicinas)

        edtId = findViewById(R.id.edtIdMedicina)
        edtNombre = findViewById(R.id.edtNombreMedicina)
        edtDescripcion = findViewById(R.id.edtDescripcion)
        edtPrecio = findViewById(R.id.edtPrecio)
        edtFechaExpiracion = findViewById(R.id.edtFechaExpiracion)
        edtProveedor = findViewById(R.id.edtProveedor)
        btnActualizar = findViewById(R.id.btnActualizarMedicina)
        btnEliminar = findViewById(R.id.btnEliminarMedicina)
        btnVolver = findViewById(R.id.btnVolverMedicina)

        btnEliminar.setOnClickListener { eliminarMedicina() }
        btnActualizar.setOnClickListener { actualizarMedicina() }
        btnVolver.setOnClickListener { volver() }

        verDatos()
    }

    private fun eliminarMedicina() {
        val id = edtId.text.toString().toInt()
        ArregloMedicina().deleteMedicina(id)
        Toast.makeText(this, "Medicina eliminada", Toast.LENGTH_LONG).show()
        volver()
    }

    private fun actualizarMedicina() {
        val id = edtId.text.toString().toInt()
        val nombre = edtNombre.text.toString()
        val descripcion = edtDescripcion.text.toString()
        val precio = edtPrecio.text.toString().toDouble()
        val fechaExpiracion = edtFechaExpiracion.text.toString()
        val proveedor = edtProveedor.text.toString()

        val medicina = Medicina(id, nombre, descripcion, precio, fechaExpiracion, proveedor)
        ArregloMedicina().updateMedicina(medicina)
        Toast.makeText(this, "Medicina actualizada", Toast.LENGTH_LONG).show()
    }

    private fun volver() {
        val intent = Intent(this, MedicinaActivity::class.java)
        startActivity(intent)
    }

    private fun verDatos() {
        val medicina = intent.getSerializableExtra("data") as Medicina
        edtId.setText(medicina.idMedicina.toString())
        edtNombre.setText(medicina.nombreM)
        edtDescripcion.setText(medicina.descripcion)
        edtPrecio.setText(medicina.precio.toString())
        edtFechaExpiracion.setText(medicina.fechaExpiracion)
        edtProveedor.setText(medicina.proveedor)
    }
}
