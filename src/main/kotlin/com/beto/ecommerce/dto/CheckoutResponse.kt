package com.beto.ecommerce.dto

import com.beto.ecommerce.domain.CarritoCompra

/**
 * Objeto response para regresar el total de la suma del carrito
 */
class CheckoutResponse (
        var costoFinal:Double,
//        var lstCarritoCompra: MutableList<List<CarritoCompra>>
        )