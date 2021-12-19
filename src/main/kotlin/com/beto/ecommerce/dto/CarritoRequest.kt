package com.beto.ecommerce.dto

/**
 * objeto request para agregar item al carrito
 */
class CarritoRequest(
        var sku:String,
        var cantidad:Int
)