package com.beto.ecommerce.controller

import com.beto.ecommerce.domain.CarritoCompra
import com.beto.ecommerce.domain.Product
import com.beto.ecommerce.dto.ActualizaCarritoRequest
import com.beto.ecommerce.dto.CarritoRequest
import com.beto.ecommerce.dto.CheckoutResponse
import com.beto.ecommerce.service.CarritoService
import com.beto.ecommerce.utils.ProductosDB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.FileNotFoundException
import javax.validation.Valid

/**
 * Controller para la gestion del carrito de compra, cuanta un cun crud controller generico para la consulta de los productos.
 */
@RestController
@RequestMapping("/api/v1/ecommerce")
class CarritoController(carritoService: CarritoService): BasicController<Product, String>(carritoService)// se quita el private val,
{
    @Autowired
    private lateinit var carritoSer: CarritoService

    /**
     * metodo post para agregar un nuevo producto al carrito
     */
    @PostMapping("/agregar/producto/carrito")
    fun agregarProductoCarrito(@Valid @RequestBody carritoRequest: CarritoRequest): ResponseEntity<Boolean> {
        carritoSer.agregarCarrito(carritoRequest);
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    /**
     * metodo get para consultar los productos en el carrito
     */
    @GetMapping("/consulta/carrito")
    fun consultaCarrito():ResponseEntity<List<CarritoCompra>>{
        return ResponseEntity(carritoSer.consultaCarrito(),HttpStatus.ACCEPTED)
    }

    /**
     * metodo put para actualizar un producto en el carrito
     */
    @PutMapping("/actualizar/producto")
    fun actualizaProductoCarrito(@Valid @RequestBody actualizaCarritoRequest: ActualizaCarritoRequest): ResponseEntity<Boolean>{
        return ResponseEntity(carritoSer.actualizaCarrito(actualizaCarritoRequest),HttpStatus.ACCEPTED)
    }

    /**
     * metodo get donde regresa el total de la compra
     */
    @GetMapping("/total")
    fun pagarCarrito() : ResponseEntity<CheckoutResponse>{
        return ResponseEntity(carritoSer.pagarCarrito(),HttpStatus.ACCEPTED)
    }
}
