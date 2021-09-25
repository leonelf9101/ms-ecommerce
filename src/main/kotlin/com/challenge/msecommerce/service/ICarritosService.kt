package com.challenge.msecommerce.service

import com.challenge.msecommerce.domain.model.dto.CrearCarritoRequestDto
import com.challenge.msecommerce.domain.model.dto.AgregarItemsCarritoRequestDto
import com.challenge.msecommerce.domain.model.entities.Carrito
import java.util.*

interface ICarritosService {
    fun obtenerCarrito(idCarrito: UUID): Carrito
    fun crearCarrito(crearCarritoRequest: CrearCarritoRequestDto): UUID?
    fun borrarItemDelCarrito(idCarritoItem: UUID)
    fun agregarItemsAlCarrito(carritoRequest: AgregarItemsCarritoRequestDto): UUID?
    fun checkout(idCarrito: UUID): Carrito
}