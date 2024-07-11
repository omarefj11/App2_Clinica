package com.example.appcolegio.controlador

import android.content.ContentValues
import com.example.appcolegio.entidad.Paciente
import com.example.appcolegio.utils.appConfig

class ArregloPaciente {

    fun listado(): ArrayList<Paciente> {
        val lista = ArrayList<Paciente>()
        // Acceder a la base de datos en modo lectura
        val cn = appConfig.BD.readableDatabase
        // Sentencia SQL
        val rs = cn.rawQuery("SELECT * FROM tb_paciente", null)
        // Bucle
        while (rs.moveToNext()) {
            val bean = Paciente(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5)
            )
            lista.add(bean)
        }
        return lista
    }

    fun savePaciente(bean: Paciente) {
        // Acceder a la base de datos en modo escritura
        val CN = appConfig.BD.writableDatabase
        // Crear objeto de la clase ContentValues
        val row = ContentValues()
        // Asignar valores a las columnas
        row.put("nombre", bean.nombre)
        row.put("apellido", bean.apellido)
        row.put("edad", bean.edad)
        row.put("genero", bean.genero)
        row.put("telefono", bean.telefono)
        // Insertar fila en la tabla tb_paciente
        CN.insert("tb_paciente", null, row)
    }

    fun updatePaciente(bean: Paciente) {
        // Acceder a la base de datos en modo escritura
        val CN = appConfig.BD.writableDatabase
        // Crear objeto de la clase ContentValues
        val row = ContentValues()
        // Asignar valores a las columnas
        row.put("nombre", bean.nombre)
        row.put("apellido", bean.apellido)
        row.put("edad", bean.edad)
        row.put("genero", bean.genero)
        row.put("telefono", bean.telefono)
        // Actualizar fila en la tabla tb_paciente
        CN.update("tb_paciente", row, "id=?", arrayOf(bean.id.toString()))
    }

    fun deletePaciente(id: Int) {
        // Acceder a la base de datos en modo escritura
        val CN = appConfig.BD.writableDatabase
        // Eliminar fila de la tabla tb_paciente
        CN.delete("tb_paciente", "id=?", arrayOf(id.toString()))
    }
}
