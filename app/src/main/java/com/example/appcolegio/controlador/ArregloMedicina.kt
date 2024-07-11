package com.example.appcolegio.controlador

import android.content.ContentValues
import com.example.appcolegio.entidad.Medicina
import com.example.appcolegio.utils.appConfig

class ArregloMedicina {

    fun listado(): ArrayList<Medicina> {
        val lista = ArrayList<Medicina>()
        val cn = appConfig.BD.readableDatabase
        val rs = cn.rawQuery("SELECT * FROM tb_medicina", null)

        while (rs.moveToNext()) {
            val bean = Medicina(
                rs.getInt(0),
                rs.getString(1),
                rs.getString(2),
                rs.getDouble(3),
                rs.getString(4),
                rs.getString(5)
            )
            lista.add(bean)
        }

        rs.close()
        return lista
    }

    fun saveMedicina(bean: Medicina) {
        val cn = appConfig.BD.writableDatabase
        val row = ContentValues().apply {
            put("nombreM", bean.nombreM)
            put("descripcion", bean.descripcion)
            put("precio", bean.precio)
            put("fechaExpiracion", bean.fechaExpiracion)
            put("proveedor", bean.proveedor)
        }
        cn.insert("tb_medicina", null, row)
    }

    fun updateMedicina(bean: Medicina) {
        val cn = appConfig.BD.writableDatabase
        val row = ContentValues().apply {
            put("nombreM", bean.nombreM)
            put("descripcion", bean.descripcion)
            put("precio", bean.precio)
            put("fechaExpiracion", bean.fechaExpiracion)
            put("proveedor", bean.proveedor)
        }
        cn.update("tb_medicina", row, "idMedicina=?", arrayOf(bean.idMedicina.toString()))
    }

    fun deleteMedicina(idMedicina: Int) {
        val cn = appConfig.BD.writableDatabase
        cn.delete("tb_medicina", "idMedicina=?", arrayOf(idMedicina.toString()))
    }
}
