package com.example.appcolegio

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.example.appcolegio.entidad.Docente
import com.example.appcolegio.utils.appConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class NuevoActivity: AppCompatActivity() {
    private lateinit var edtNombre: EditText
    private lateinit var edtPaterno: EditText
    private lateinit var edtMaterno: EditText
    private lateinit var edtSueldo: EditText
    private lateinit var edtHijos: EditText
    private lateinit var btnGrabar: Button
    private lateinit var btnVolver: Button

    //declarar un obj que referencie a la bd de firebase
    private lateinit var BD:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nuevo_activity)
        edtNombre=findViewById(R.id.edtNombres)
        edtPaterno=findViewById(R.id.edtPaterno)
        edtMaterno=findViewById(R.id.edtMaterno)
        edtSueldo=findViewById(R.id.edtSueldo)
        edtHijos=findViewById(R.id.edtHijos)
        btnGrabar=findViewById(R.id.btnGrabar)
        btnVolver=findViewById(R.id.btnVolver)

        btnGrabar.setOnClickListener({grabar()})
        btnVolver.setOnClickListener({volver()})
        conectar()
    }
    fun grabar(){
        //generar ID
        val key=BD.push().key

        //leer cajas
        var nom=edtNombre.text.toString()
        var pat=edtPaterno.text.toString()
        var mat=edtMaterno.text.toString()
        var sue=edtSueldo.text.toString().toDouble()
        var hijos=edtHijos.text.toString().toInt()
        //crear objeto de la clase Docente y enviar los valores de las variables
        var doc=Docente(nom,pat,mat,sue,hijos,key!!)

        BD.child("docente").child(key!!).setValue(doc).addOnCompleteListener {
            Toast.makeText(this,"Docente Registrado",Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this,"Docente registrado",Toast.LENGTH_LONG).show()
    }
    fun volver(){
        var intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    //funcion para conectar a la bd firebase
    fun conectar(){
        //iniciar firebase en la clase actual
        FirebaseApp.initializeApp(appConfig.CONTEXT)
        //crear el obj bd
        BD=FirebaseDatabase.getInstance().reference
    }

}