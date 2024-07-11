package com.example.appcolegio.entidad

import java.io.Serializable

class Paciente(var id: Int,
               var nombre: String,
               var apellido: String,
               var edad: Int,
               var genero: String,
               var telefono: String) : Serializable {

}

