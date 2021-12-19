package com.beto.ecommerce.configuration

import com.beto.ecommerce.ProductApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc

/* Para configurar el swagger https://github.com/springfox/springfox/issues/3052*/
@Configuration
@EnableSwagger2WebMvc
//@Import(SpringDataRestConfiguration :: class)
class Swagger {

    @Bean//para mandar el bean al contenedor
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(ProductApplication :: class.java.`package`.name)) //analizar todo el proyecto
            .paths(PathSelectors.any())
            .build()
}