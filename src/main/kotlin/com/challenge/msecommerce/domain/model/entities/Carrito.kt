package com.challenge.msecommerce.domain.model.entities

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@JsonIgnoreProperties(ignoreUnknown = true)
class Carrito(
    val idCarrito: UUID?,
    val estado: EnumEstadoCarrito,
    val itemsCarrito: List<CarritoItem>?
) {
    val total = itemsCarrito?.sumOf {
        it.producto.obtenerPrecio().multiply(BigDecimal.valueOf(it.cantidad.toDouble())).setScale(2, RoundingMode.HALF_EVEN)
    }
}