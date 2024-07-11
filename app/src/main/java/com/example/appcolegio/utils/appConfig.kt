package com.example.appcolegio.utils

import android.app.Application
import android.content.Context
import com.example.appcolegio.data.InitBD

class appConfig:Application() {

    companion object{
        lateinit var CONTEXT:Context
        lateinit var BD:InitBD
        var BD_NAME="consorcio.bd"
        var VERSION=1
    }
    override fun onCreate() {
        super.onCreate()
        CONTEXT =applicationContext
        BD=InitBD()
    }

}