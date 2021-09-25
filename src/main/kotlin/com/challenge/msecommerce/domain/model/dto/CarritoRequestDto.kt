package com.challenge.msecommerce.domain.model.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
open class CrearCarritoRequestDto(
    open val itemsCarrito: List<CarritoItemRequestDto>
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class CarritoItemRequestDto(
    val idProducto: UUID,
    val cantidad: Integer
)


fun CarritoItemRequestDto.toCarritoItemDto(
    carritoDto: CarritoDto?
): CarritoItemDto =
    CarritoItemDto(
        carritoDto = carritoDto,
        productoDto = ProductoDto(idProducto),
        cantidad = cantidad
    )

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class AgregarItemsCarritoRequestDto(
    val idCarrito: UUID,
    itemsCarrito: List<CarritoItemRequestDto>
) : CrearCarritoRequestDto(itemsCarrito)