package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DatosActivity: AppCompatActivity() {

    private lateinit var edtNombre: EditText
    private lateinit var edtPaterno: EditText
    private lateinit var edtMaterno: EditText
    private lateinit var edtSueldo: EditText
    private lateinit var edtHijos: EditText
    private lateinit var btnActualizar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnVolver: Button

    private lateinit var bean:Docente

    //declarar un obj que referencie a la bd de firebase
    private lateinit var BD: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datos_docente)


        edtNombre=findViewById(R.id.edtNombreA)
        edtPaterno=findViewById(R.id.edtApellidoA)
        edtMaterno=findViewById(R.id.edtEdadA)
        edtSueldo=findViewById(R.id.edtGeneroA)
        edtHijos=findViewById(R.id.edtTelefonoA)
        btnActualizar=findViewById(R.id.btnActualizarPaciente)
        btnVolver=findViewById(R.id.btnVolverPaciente)
        btnEliminar=findViewById(R.id.btnEliminarPaciente)
        btnEliminar.setOnClickListener({eliminar()})
        btnActualizar.setOnClickListener({actualizar()})
        btnVolver.setOnClickListener({volver()})
        verDatos()
        conectar()
    }
    fun eliminar(){
        BD.child("docente").child(bean.id).removeValue().addOnCompleteListener {
            Toast.makeText(this,"Docente Eliminado",Toast.LENGTH_LONG).show()
        }
    }

    fun actualizar(){
        //leer cajas

        var nom=edtNombre.text.toString()
        var pat=edtPaterno.text.toString()
        var mat=edtMaterno.text.toString()
        var sue=edtSueldo.text.toString().toDouble()
        var hijos=edtHijos.text.toString().toInt()

        bean.nombre=nom
        bean.paterno=pat
        bean.materno=mat
        bean.sueldo=sue
        bean.hijos=hijos

        BD.child("docente").child(bean.id).setValue(bean).addOnCompleteListener {
            Toast.makeText(this,"Docente Registrado",Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this,"Docente registrado",Toast.LENGTH_LONG).show()
    }
    fun volver(){
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun verDatos(){
        //recuperar clave "data" que es de tipo Docente
        bean=intent.getSerializableExtra("data") as Docente

        edtNombre.setText(bean.nombre)
        edtPaterno.setText(bean.paterno)
        edtMaterno.setText(bean.materno)
        edtSueldo.setText(""+bean.sueldo)
        edtHijos.setText(""+bean.hijos)
    }

    //funcion para conectar a la bd firebase
    fun conectar(){
        //iniciar firebase en la clase actual
        FirebaseApp.initializeApp(appConfig.CONTEXT)
        //crear el obj bd
        BD= FirebaseDatabase.getInstance().reference
    }
}