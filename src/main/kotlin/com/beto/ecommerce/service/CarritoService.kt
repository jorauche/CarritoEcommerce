package com.beto.ecommerce.service

import com.beto.ecommerce.domain.CarritoCompra
import com.beto.ecommerce.domain.Product
import com.beto.ecommerce.dto.ActualizaCarritoRequest
import com.beto.ecommerce.dto.CarritoRequest
import com.beto.ecommerce.dto.CheckoutResponse
import com.beto.ecommerce.utils.EstadoEnum
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*



@Service
class CarritoService: BasicCrud<Product, String> {
	private val logger = LoggerFactory.getLogger(javaClass)
	private val lstProducto:MutableSet<Product> = mutableSetOf(Product(UUID.randomUUID().toString(),"Bocinas lG","BOC-LGP-NEG-XS-56897","Bocinas LG 12 pulgadas",2596.00,"simple")
	, Product(UUID.randomUUID().toString(),"Mouse LGT23 lG","LGT-GAP-NEG-1256","Mause Logitech inhalambrico",250.60,"descuento")
	,Product(UUID.randomUUID().toString(),"Kindle E-Reader","KDL-10-GEN-785623","E-reader Kindle, con una luz frontal, color Negro, 10ª generación - 2019",1400.00,"simple")
	,Product(UUID.randomUUID().toString(),"Hisense 55 R6000GM 4K UHD Roku TV","HIN-R6000GM-PLAT-1025887","Hisense 55 R6000GM 4K UHD Roku TV, HDR Dolby Vision (55R6000GM, 2020) (Reacondicionado)",15000.60,"descuento"))

	private val lstCarritoCompra: MutableSet<CarritoCompra> = mutableSetOf()
	override fun findAll(): List<Product> {
		logger.info("COnsulta de todos los productos")
		return lstProducto.toList()
	}

	override fun findById(id: String): Product? = this.lstProducto.find { it.sku == id }

	override fun save(t: Product): Boolean= this.lstProducto.add(t);

	override fun update(t: Product): Boolean {
		lstProducto.removeIf { it.uuid==t.uuid }
		logger.info("Actualizando Producto", lstProducto)
		return this.lstProducto.add(t)
	}
	override fun deleteById(id: String): Boolean = this.lstProducto.remove(this.findById(id))

	fun agregarCarrito(carritoRequest: CarritoRequest):Boolean{
		if(lstCarritoCompra.isEmpty()){
			logger.info("Esta vacio, se agrega nuevo producto al carrito")
			actualizalstCarrito(carritoRequest)
			return true
		}else{
			for(carrito in lstCarritoCompra){
				if(carrito.producto.sku.equals(carritoRequest.sku)){
					carrito.cantidad += carritoRequest.cantidad
				}else{
					actualizalstCarrito(carritoRequest)
				}
				return true;
			}
		}
		return false
	}

	fun actualizalstCarrito(carritoRequest: CarritoRequest){
		var desc:Double=00.00
		for(producto in lstProducto){
			if (carritoRequest.sku.equals(producto.sku)){
				var carritoCompra = CarritoCompra(UUID.randomUUID().toString(), Product(UUID.randomUUID().toString(),producto.nombre,producto.sku,
						producto.descripcion,producto.precio,producto.descuento),carritoRequest.cantidad,EstadoEnum.PENDIENTE)
				if(producto.descuento.equals("descuento")) {
					desc = producto.precio!!
					desc /= 2
				}
				carritoCompra.producto.precio = desc

				lstCarritoCompra.add(carritoCompra)
				logger.info("Se agrega producto al carrito de compra")
			}
		}
	}

	fun actualizaCarrito (actualizaCarritoRequest: ActualizaCarritoRequest) : Boolean{
		for (carritoCompra in lstCarritoCompra){
			if(carritoCompra.producto.sku.equals(actualizaCarritoRequest.carritoCompra.producto.sku)){
				logger.info("Actualiza producto ")
				lstCarritoCompra.removeIf { it.producto.sku==actualizaCarritoRequest.carritoCompra.producto.sku }
				lstCarritoCompra.add(actualizaCarritoRequest.carritoCompra)
				return true
			}
		}
		return false
	}

	fun pagarCarrito() : CheckoutResponse{
		var totalCompra: Double=00.00
		var lstCompraProductos : MutableList<List<CarritoCompra>> = ArrayList()
		logger.info("Se genera el costo total de la compra")
		for(carrito in lstCarritoCompra){
			totalCompra = carrito.producto.precio?.times(carrito.cantidad)!!
			carrito.estado = EstadoEnum.COMPLETADO
//			lstCompraProductos.forEachIndexed{index,it -> lstCompraProductos.add(index,it) }

		}
		return CheckoutResponse(totalCompra)
	}

	fun consultaCarrito(): List<CarritoCompra>{
		return lstCarritoCompra.toList()
	}




}