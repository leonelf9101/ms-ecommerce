package com.challenge.msecommerce.domain.model.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
open class ProductoSimple(
    open val idProducto: UUID,
    nombre: String?,
    sku: String?,
    descripcion: String?,
    precioSinDescuento: Double
) : Producto(nombre, sku, descripcion, precioSinDescuento)