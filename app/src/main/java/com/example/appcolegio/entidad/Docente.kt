package com.example.appcolegio.entidad

import java.io.Serializable

class Docente(
               var nombre: String,
                var paterno: String,
                var materno: String,
                var sueldo: Double,
                var hijos: Int,
                var id: String):Serializable{
}

