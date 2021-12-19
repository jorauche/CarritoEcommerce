package com.beto.ecommerce.domain

import com.beto.ecommerce.utils.EstadoEnum
import jdk.nashorn.internal.ir.annotations.Ignore
import java.util.*
import javax.validation.constraints.Min

/**
 * entities para producto y carrito compra con su respectivo generador de UUID
 */
class Product(
        @Ignore
        val uuid:String?=UUID.randomUUID().toString(),
        var nombre:String,
        var sku:String,
        var descripcion :String,

        var precio:Double?=00.0,
        var descuento:String?="simple"


)

class CarritoCompra(
        @Ignore
        val uuid: String?= UUID.randomUUID().toString(),
        var producto:Product,
        var cantidad:Int,
        var estado : EstadoEnum
)