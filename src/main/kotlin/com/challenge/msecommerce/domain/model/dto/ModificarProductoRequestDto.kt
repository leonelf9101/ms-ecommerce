package com.challenge.msecommerce.domain.model.dto

import com.challenge.msecommerce.domain.model.entities.Producto
import com.challenge.msecommerce.domain.model.entities.ProductoSimple
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class ModificarProductoRequestDto(
    idProducto: UUID,
    nombre: String,
    sku: String,
    descripcion: String,
    precioSinDescuento: Double,
    val tieneDescuento: Boolean
) : ProductoSimple(idProducto, nombre, sku, descripcion, precioSinDescuento)

fun ModificarProductoRequestDto.toProductoDto(): ProductoDto =
    ProductoDto(
        idProducto = idProducto,
        nombre = nombre,
        sku = sku,
        descripcion = descripcion,
        precioSinDescuento = precioSinDescuento,
        tieneDescuento = tieneDescuento
    )
