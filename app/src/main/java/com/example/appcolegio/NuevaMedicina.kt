package com.example.appcolegio

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appcolegio.controlador.ArregloMedicina
import com.example.appcolegio.entidad.Medicina
import java.text.SimpleDateFormat
import java.util.*

class NuevaMedicina : AppCompatActivity() {
    private lateinit var edtNombreM: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtPrecio: EditText
    private lateinit var edtProveedor: EditText
    private lateinit var btnGrabarMedicina: Button
    private lateinit var btnVolverMedicina: Button
    private lateinit var edtFechaExpiracion: EditText
    private lateinit var btnCalendario: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nueva_medicina)

        edtNombreM = findViewById(R.id.edtNombre)
        edtDescripcion = findViewById(R.id.edtDescripcion)
        edtPrecio = findViewById(R.id.edtPrecio)
        edtProveedor = findViewById(R.id.edtProveedor)
        edtFechaExpiracion = findViewById(R.id.edtFechaExpiracion)
        btnCalendario = findViewById(R.id.btnCalendario)
        btnGrabarMedicina = findViewById(R.id.btnGrabarMedicina)
        btnVolverMedicina = findViewById(R.id.btnVolverMedicina)

        // Configurar DatePicker para mostrar fecha actual
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        edtFechaExpiracion.setText(dateFormat.format(calendar.time))

        // Escuchar clic en el botón de calendario
        btnCalendario.setOnClickListener {
            mostrarDatePicker()
        }

        btnGrabarMedicina.setOnClickListener { grabarMedicina() }
        btnVolverMedicina.setOnClickListener { volver() }
    }

    private fun mostrarDatePicker() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                calendar.set(year, month, day)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                edtFechaExpiracion.setText(dateFormat.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun grabarMedicina() {
        // Leer datos de las cajas de texto
        val nombreM = edtNombreM.text.toString()
        val descripcion = edtDescripcion.text.toString()
        val precio = edtPrecio.text.toString().toDouble()
        val fechaExpiracion = edtFechaExpiracion.text.toString()
        val proveedor = edtProveedor.text.toString()

        // Crear objeto Medicina con los datos ingresados
        val medicina = Medicina(0, nombreM, descripcion, precio, fechaExpiracion, proveedor)

        // Guardar medicina usando el controlador ArregloMedicina
        ArregloMedicina().saveMedicina(medicina)

        // Mostrar mensaje de confirmación
        Toast.makeText(this, "Medicina registrada", Toast.LENGTH_LONG).show()
    }

    private fun volver() {
        val intent = Intent(this, MedicinaActivity::class.java)
        startActivity(intent)
    }
}
