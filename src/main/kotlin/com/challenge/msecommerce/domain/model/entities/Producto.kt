package com.challenge.msecommerce.domain.model.entities

import com.challenge.msecommerce.domain.model.dto.ProductoDto
import com.challenge.msecommerce.exceptions.CampoRequeridoException
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.RoundingMode
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
open class Producto(
    open val nombre: String?,
    open val sku: String?,
    open val descripcion: String?,
    open val precioSinDescuento: Double
) {
    open fun obtenerPrecio() = precioSinDescuento.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)
}

