package com.challenge.msecommerce.domain.model.entities

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class ProductoDescuento(
    idProducto: UUID,
    nombre: String?,
    sku: String?,
    descripcion: String?,
    precioSinDescuento: Double
) : ProductoSimple(idProducto, nombre, sku, descripcion, precioSinDescuento) {
    val precioConDescuento: BigDecimal = precioSinDescuento.div(2).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)

    override fun obtenerPrecio() = precioConDescuento

}