package com.challenge.msecommerce.controller

import com.challenge.msecommerce.domain.model.dto.CrearProductoRequestDto
import com.challenge.msecommerce.domain.model.entities.ProductoSimple
import com.challenge.msecommerce.domain.model.dto.ProductoDto
import com.challenge.msecommerce.domain.model.dto.ModificarProductoRequestDto
import com.challenge.msecommerce.domain.model.entities.Producto
import com.challenge.msecommerce.service.IProductosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
class ProductosController (
    val productosService : IProductosService
) {

    @GetMapping("/productos")
    fun obtenerProductos(): ResponseEntity<List<Producto>> {
        return ResponseEntity(productosService.obtenerProductos(),HttpStatus.OK)
    }

    @PostMapping("/producto")
    fun crearProducto(
        @RequestBody
        producto: CrearProductoRequestDto
    ): ResponseEntity<Producto> {
        return ResponseEntity<Producto>(productosService.crearProducto(producto), HttpStatus.OK)
    }

    @PutMapping("/producto")
    fun modificarProducto(
        @RequestBody
        producto: ModificarProductoRequestDto
    ): ResponseEntity<Producto> {
        return ResponseEntity<Producto>(productosService.modificarProducto(producto), HttpStatus.OK)
    }

    @DeleteMapping("/producto/{id}")
    fun borrarProducto(@PathVariable id: UUID): ResponseEntity<Any> {
        productosService.borrarProducto(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}