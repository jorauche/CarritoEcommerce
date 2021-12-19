package com.beto.ecommerce.utils

import java.io.File
import java.io.InputStream
import java.util.*

class ProductosDB {

    fun lecturaArchivo(){
        val inputStream: InputStream = File(".//resources//productos.txt").inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }
        println(inputString) // muestra en consola todo el archivo

    }

    fun crearUUID(){
        var uuid = UUID.randomUUID()
    }
}