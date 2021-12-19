package com.beto.ecommerce.controller
import com.beto.ecommerce.service.BasicCrud
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.xml.ws.Response

/**
 * Metodo generido para cun crud controller
 */
abstract class BasicController<T,ID> (private val basicCrud: BasicCrud<T, ID>) {

    @ApiOperation("Obtener todas la entidades")
    @GetMapping("/obtener/productos")
    fun findAll() = basicCrud.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id:ID) : ResponseEntity<T>{
        val entity =basicCrud.findById(id)
        return ResponseEntity.status(if(entity !=null) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)
    }

    @PostMapping
    fun save(@Valid @RequestBody product: T): ResponseEntity<Boolean> {
        val entity = basicCrud.save(product)
        return ResponseEntity.status(if (entity) HttpStatus.CREATED else HttpStatus.CONFLICT).body(entity)//en el if es por recibe un boleano
    }

    @PutMapping
    fun update(@RequestBody product: T): ResponseEntity<Boolean> {
        val entity = basicCrud.update(product)
        return ResponseEntity.status(if(entity) HttpStatus.OK else HttpStatus.CONFLICT).body(entity)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id:ID): ResponseEntity<Boolean> {
        val entity = basicCrud.deleteById(id)
        return ResponseEntity.status(if(entity) HttpStatus.OK else HttpStatus.NO_CONTENT).body(entity)
    }
}