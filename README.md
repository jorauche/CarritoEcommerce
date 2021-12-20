# Carrito de compra
## Proyecto en springboot y kottlin

## Introducción

Este proyecto esta basado en SpringBoot 2.5.7 usando Junit , el cual ese exponen servicios rest para la administraciond de un carrito de compras

## Ambiente de desarrollo

En el trascurso del proyecto vamos a usar las siguientes herramientas y siguientes configuracion así como los lenguajes de desarrollo

* [Maven](https://maven.apache.org/)
* [Kotlin](https://kotlinlang.org/docs/reference/)
* [Junit](https://junit.org/junit5/)
* [Intellij](https://www.jetbrains.com/es-es/idea/)

<!-- Empezamos -->
## Empezamos

La estructura del proyecto se utilizo maven y  kotlin como lenguaje de programación apoyandonos con el framework de spring boot, se baso en un patron MVC, la simulación de los datos se tiene un lista donde se hace la administración del carrito con los servicios rest.
Para exponer los servicios , como las buenas prácticas se usaron adecuadamente los verbos POST,PUT,DELETE Y GET dependiendo el tipo de operación a realizar en el carrito de compra.

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/EstructuraProyecto.png)

* Contamos con pruebas unitarias en el proyecto 
![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/PruebasUnitarias.png)

## Ejecutando las pruebas ⚙️
Las pruebas se realizaron desde el postman

* Agregar un producto al catálogo

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/AgregarProducto.png)

* Actualizar un producto del catálogo

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ActualizaProducto.png)

* Consultar el producto por medio del sku 

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ConsultaProductoSKU.png)

* Eliminar un producto del catalgo

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/EliminarProducto.png)

* Obtener todos los productos

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ObtenerProductos.png)

### Administración del carrito de compra

* Agregar un producto al carrito de compra, junto agregando la cantidad que desea comprar el cliente

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/AgregarProductoCarrito.png)

* Consulta los productos agregados al carrito de compra

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ConsultaProductosCarrito.png)

* Actualiza un producto del carrito de compra

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ActualizaProductoCarrito.png)

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/ResultadoActualizaCarritoProducto.png)

* Por ultimo el resultado de la compra y los productos en completado.

![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/TotalCompra.png)
![alt text](https://github.com/jorauche/CarritoEcommerce/blob/master/Pantallas/CarritoEstatusCompletado.png)

