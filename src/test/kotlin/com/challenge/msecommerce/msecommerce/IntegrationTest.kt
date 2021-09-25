package com.challenge.msecommerce.msecommerce

import com.challenge.msecommerce.MsecommerceApplication
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.net.URI

@SpringBootTest(
    classes=[MsecommerceApplication::class],
    properties = [
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.datasource.url=jdbc:h2:mem:msecommerce",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
    ]
)
@AutoConfigureMockMvc
class IntegrationTest(
) {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun correrFlujoCompleto(){
        crearProducto()
        obtenerProductos()
    }

    fun crearProducto(){
        mockMvc.perform(
            post(URI.create("/producto"))
                .content("""{
                    "nombre": "nombre1",
                    "sku" : "sku1",
                    "descripcion" : "descr1",
                    "precio_sin_descuento" : 100.10,
                    "tiene_descuento": true
                }""")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id_producto", Matchers.notNullValue()))
            .andExpect(jsonPath("$.nombre", Matchers.`is`("nombre1")))
            .andExpect(jsonPath("$.sku", Matchers.`is`("sku1")))
            .andExpect(jsonPath("$.descripcion", Matchers.`is`("descr1")))
            .andExpect(jsonPath("$.precio_sin_descuento", Matchers.`is`(100.10)))
            .andExpect(jsonPath("$.precio_con_descuento", Matchers.`is`(50.05)))

    }

    fun obtenerProductos() {
        mockMvc.perform(
            get(URI.create("/productos"))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
            .andExpect(content().contentType("application/json"))
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[0].id_producto", Matchers.notNullValue()))
            .andExpect(jsonPath("$[0].nombre", Matchers.`is`("nombre1")))
            .andExpect(jsonPath("$[0].sku", Matchers.`is`("sku1")))
            .andExpect(jsonPath("$[0].descripcion", Matchers.`is`("descr1")))
            .andExpect(jsonPath("$[0].precio_sin_descuento", Matchers.`is`(100.10)))
            .andExpect(jsonPath("$[0].precio_con_descuento", Matchers.`is`(50.05)))
    }


}