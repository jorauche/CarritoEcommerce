package com.beto.ecommerce

import com.beto.ecommerce.utils.ProductosDB
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductApplication

fun main(args: Array<String>) {
	runApplication<ProductApplication>(*args)

}
