package com.challenge.msecommerce.domain.model.dto

import com.challenge.msecommerce.domain.model.entities.Producto
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class CrearProductoRequestDto(
    nombre: String,
    sku: String,
    descripcion: String,
    precioSinDescuento: Double,
    val tieneDescuento: Boolean
) : Producto(nombre, sku, descripcion, precioSinDescuento)

fun CrearProductoRequestDto.toProductoDto() : ProductoDto =
    ProductoDto(
        nombre = nombre,
        sku = sku,
        descripcion = descripcion,
        precioSinDescuento = precioSinDescuento,
        tieneDescuento = tieneDescuento
    )