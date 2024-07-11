package com.example.appcolegio.entidad

import java.io.Serializable

class Medicina(
    var idMedicina: Int,
    var nombreM: String,
    var descripcion: String,
    var precio: Double,
    var fechaExpiracion: String,
    var proveedor: String
) : Serializable {

}
