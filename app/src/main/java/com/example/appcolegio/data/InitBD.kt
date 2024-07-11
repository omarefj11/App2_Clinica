package com.example.appcolegio.data

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appcolegio.utils.appConfig

class InitBD : SQLiteOpenHelper(
    appConfig.CONTEXT,
    appConfig.BD_NAME,
    null,
    appConfig.VERSION
) {
    override fun onCreate(bd: SQLiteDatabase) {
        // Crear la tabla tb_docente
        bd.execSQL(
            "CREATE TABLE tb_docente(" +
                    "cod INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom VARCHAR(30)," +
                    "pat VARCHAR(30)," +
                    "mat VARCHAR(30)," +
                    "sue DOUBLE," +
                    "hijos INTEGER)"
        )
        bd.execSQL("INSERT INTO tb_docente VALUES(null, 'Luis', 'Mora', 'Oca', 2500, 2)")

        // Crear la tabla tb_paciente
        bd.execSQL(
            "CREATE TABLE tb_paciente(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre VARCHAR(50)," +
                    "apellido VARCHAR(50)," +
                    "edad INTEGER," +
                    "genero VARCHAR(10)," +
                    "telefono VARCHAR(15))"
        )
        bd.execSQL("INSERT INTO tb_paciente VALUES(null, 'Juan', 'Perez', 30, 'Masculino', '123456789')")

        // Crear la tabla tb_medicina
        bd.execSQL(
            "CREATE TABLE tb_medicina(" +
                    "idMedicina INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombreM VARCHAR(50)," +
                    "descripcion TEXT," +
                    "precio DOUBLE," +
                    "fechaExpiracion VARCHAR(20)," +
                    "proveedor VARCHAR(50))"
        )
        bd.execSQL("INSERT INTO tb_medicina VALUES(null, 'Paracetamol', 'Medicamento para aliviar el dolor y bajar la fiebre', 10.5, '2024-12-31', 'Farmacia ABC')")

        // Crear la tabla tb_laboratorio
        bd.execSQL(
            "CREATE TABLE tb_laboratorio(" +
                    "idLaboratorio INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombreLaboratorio VARCHAR(50)," +
                    "direccion TEXT," +
                    "telefono VARCHAR(15))"
        )
        bd.execSQL("INSERT INTO tb_laboratorio VALUES(null, 'Laboratorio ABC', 'Av. Principal 123', '987654321')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Método necesario para la gestión de actualizaciones de la base de datos.
        // En esta implementación, no se está gestionando ninguna actualización.
    }
}
