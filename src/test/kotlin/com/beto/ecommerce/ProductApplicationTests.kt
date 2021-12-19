package com.beto.ecommerce


import com.beto.ecommerce.domain.CarritoCompra
import com.beto.ecommerce.domain.Product
import com.beto.ecommerce.dto.CarritoRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.beto.ecommerce.service.CarritoService
import com.beto.ecommerce.utils.EstadoEnum
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@SpringBootTest
//@AutoConfigureMockMvc// ahora el siguiente paso es por configuracion manual
class ProductApplicationTests {

	@Autowired private lateinit var mapper: ObjectMapper
	@Autowired private lateinit var webApplicationContex: WebApplicationContext

	private  val productEndPoint = "/api/v1/ecommerce/"
	//@Autowired private  lateinit var mockMvc:MockMvc
	private val mockMvc: MockMvc by lazy{
		MockMvcBuilders.webAppContextSetup(webApplicationContex)
				.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print()).build()
	}
	@Autowired private lateinit var productService: CarritoService

	/*@Test
	fun findAll() {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product"))
				.andExpect(status().isConflict)
	}*/
	@Test
	@Order(1)
	fun a_findAll() {
		val productFromService = productService.findAll()

		val products:List<Product> = mockMvc.perform(MockMvcRequestBuilders.get(productEndPoint.plus("obtener/productos")))
				.andExpect(status().isOk)
				.bodyTo(mapper)// se creo un funcion nueva y se extendio la funcionalidd ResultActions
		assert(products.isNotEmpty())
	}

	@Test
	@Order(4)
	fun d_saveCarrito(){
//		val product = Product(name="PineApple", price = 50.0)
		val productFromService = productService.findAll()
		val request = CarritoRequest("LGT-GAP-NEG-1256",2)
		val response = mockMvc.perform(MockMvcRequestBuilders.post(productEndPoint.plus("agregar/producto/carrito"))
				//.body(data = product,mapper = mapper)) nuevao funcionalidad que hace lo mismo abajo
				.content(mapper.writeValueAsBytes(request)).contentType(MediaType.APPLICATION_JSON_UTF8))// con el UF8 me muestra el body, si no lo tiene se imprime
				.andExpect(status().isAccepted)


		val carritCompra = productService.consultaCarrito()
		assert(carritCompra.isNotEmpty())
	}

	@Test
	@Order(8)
	fun a_ConsultaCarrito() {

		val carrito = productService.consultaCarrito()

		val products:List<CarritoCompra> = mockMvc.perform(MockMvcRequestBuilders.get(productEndPoint.plus("consulta/carrito")))
				.andExpect(status().isOk)
				.bodyTo(mapper)
		assert(products.isNotEmpty())
	}
	@Test
	@Order(2)
	fun b_findById(){
		val productsFromService= productService.findAll()
		assert(!productsFromService.isEmpty()){"No puede ser vacio"}// si eso falla , manda mensaje de error
		val product =  productsFromService.first()

		mockMvc.perform(MockMvcRequestBuilders.get("$productEndPoint/${product.uuid}"))
				.andExpect(status().isOk)
				.andExpect(jsonPath("$.uuid", Matchers.`is`(product.uuid)))
	}
	@Test
	@Order(3)
	fun c_findByIdEmpty(){
		mockMvc.perform(MockMvcRequestBuilders.get("$productEndPoint/${UUID.randomUUID()}"))
				.andExpect(status().isNoContent)
				.andExpect(jsonPath("$").doesNotExist())// si es nulo , que no existe
	}

	@Test
	@Order(4)
	fun d_saveSuccessfully(){
//		val product = Product(name="PineApple", price = 50.0)
		val product= Product(UUID.randomUUID().toString(),"Iphone 8","IPHONE-NEG-78956","IPHONE 8 ULTIMA GENERACION NEGRO",45800.60,"descuento")
		val result: Boolean = mockMvc.perform(MockMvcRequestBuilders.post(productEndPoint)
				//.body(data = product,mapper = mapper)) nuevao funcionalidad que hace lo mismo abajo
				.content(mapper.writeValueAsBytes(product)).contentType(MediaType.APPLICATION_JSON_UTF8))// con el UF8 me muestra el body, si no lo tiene se imprime
				.andExpect(status().isCreated)
				.bodyTo(mapper)

		assert(result)
	}

	@Test
	@Order(5)
	fun d_updateSucessFully(){
		val productsFromService= productService.findAll()
		assert(!productsFromService.isEmpty()){"No puede ser vacio"}// si eso falla , manda mensaje de error
		val product =  productsFromService.first().apply { precio = 44.23 }
		//val price = product.price.apply { 22.30 }
		val result: Boolean = mockMvc.perform(MockMvcRequestBuilders.put(productEndPoint)
				.body(data = product,mapper = mapper))
				.andExpect(status().isOk)
				.bodyTo(mapper)
		assert(result)
	}

	@Test
	@Order(6)
	fun g_deleteById(){
		val productsFromService= productService.findAll()
		assert(!productsFromService.isEmpty()){"No puede ser vacio"}
		val product =  productsFromService.last()

		val result: Boolean = mockMvc.perform(MockMvcRequestBuilders.delete("$productEndPoint/${product.sku}"))
				.andExpect(status().isOk)
				.bodyTo(mapper)
		assert(result)

		assert(!productService.findAll().contains(product))
	}
}
