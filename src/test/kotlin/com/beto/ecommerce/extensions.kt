package com.beto.ecommerce

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder


inline fun <reified T> ResultActions.bodyTo(mapper : ObjectMapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()) : T{
    return mapper.readValue(this.andReturn().response.contentAsString, object :TypeReference<T>(){})
}
// se va usar otro parametro para adjuntar un objectmapper y va tener un valor por default
//recibe otro paramtro que es el mediaType para tipo de aplicacion
fun MockHttpServletRequestBuilder.body(data:Any, mapper: ObjectMapper = jacksonObjectMapper(),
                                       mediaType:MediaType=MediaType.APPLICATION_JSON_UTF8): MockHttpServletRequestBuilder {//MediaType.APPLICATION_JSON
    return this.content(mapper.writeValueAsBytes(data)).contentType(mediaType)
}