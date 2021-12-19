package com.beto.ecommerce.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.FileNotFoundException

//anteriormete regresabamos un mapa de errores y con su traza de error, ahora va ser tratado esta excepcion y solo se regresa lo definido.
@ControllerAdvice
class RestResponseEntityErrorHandler: ResponseEntityExceptionHandler() {
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val result:Map<String,List<String?>> = ex.bindingResult.fieldErrors.groupBy({it.field},{it.defaultMessage})// con la funcio agrupamos uno o mas valores
    return ResponseEntity.status(status).headers(headers).body(result)
    }


    @ExceptionHandler(FileNotFoundException :: class)//:: para convertirlo a clase de kotlin
    fun test(fileNotFoundException : FileNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ups error")

    }
}