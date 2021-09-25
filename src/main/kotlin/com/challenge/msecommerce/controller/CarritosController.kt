package com.challenge.msecommerce.controller

import com.challenge.msecommerce.domain.model.dto.CrearCarritoRequestDto
import com.challenge.msecommerce.domain.model.dto.AgregarItemsCarritoRequestDto
import com.challenge.msecommerce.domain.model.dto.MessageResponseDto
import com.challenge.msecommerce.domain.model.entities.Carrito
import com.challenge.msecommerce.service.ICarritosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
@RestController
class CarritosController (
    val carritosService : ICarritosService
) {

    @GetMapping("/carrito/consultar/{idCarrito}")
    fun obtenerCarrito(
        @PathVariable
        idCarrito: UUID
    ): ResponseEntity<Carrito> =
        ResponseEntity(carritosService.obtenerCarrito(idCarrito),HttpStatus.OK)

    @PostMapping("/carrito/crear")
    fun crearCarrito(
        @RequestBody
        crearCarritoRequest: CrearCarritoRequestDto
    ): ResponseEntity<MessageResponseDto> {
        val idCarritoCreado = carritosService.crearCarrito(crearCarritoRequest)
        val response = MessageResponseDto("Carrito con id $idCarritoCreado creado correctamente !")
        return ResponseEntity<MessageResponseDto>(response, HttpStatus.OK)
    }

    @PutMapping("/carrito/agregarItems")
    fun modificarCarrito(
        @RequestBody
        agregarItemsCarritoRequest: AgregarItemsCarritoRequestDto
    ): ResponseEntity<MessageResponseDto> {
        carritosService.agregarItemsAlCarrito(agregarItemsCarritoRequest)
        val response = MessageResponseDto("Carrito con id ${agregarItemsCarritoRequest.idCarrito} modificado correctamente !")
        return ResponseEntity<MessageResponseDto>(response, HttpStatus.OK)
    }

    @DeleteMapping("/carrito/eliminarItem/{idCarritoItem}")
    fun modificarCarrito(
        @PathVariable
        idCarritoItem: UUID
    ): ResponseEntity<MessageResponseDto> {
        carritosService.borrarItemDelCarrito(idCarritoItem)
        val response = MessageResponseDto("Item con id $idCarritoItem eliminado del carrito correctamente !")
        return ResponseEntity<MessageResponseDto>(response, HttpStatus.OK)
    }

    @PostMapping("/carrito/checkout/{idCarrito}")
    fun checkout(
        @PathVariable
        idCarrito: UUID
    ): ResponseEntity<MessageResponseDto> {
        val carritoCompletado = carritosService.checkout(idCarrito)
        val response = MessageResponseDto("Checkout de carrito con id ${carritoCompletado.idCarrito} realizado correctamente !")
        return ResponseEntity<MessageResponseDto>(response, HttpStatus.OK)
    }

}


