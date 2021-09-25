package com.challenge.msecommerce.domain.model.dto

import com.challenge.msecommerce.domain.model.entities.Producto
import com.challenge.msecommerce.domain.model.entities.ProductoDescuento
import com.challenge.msecommerce.domain.model.entities.ProductoSimple
import com.challenge.msecommerce.exceptions.CampoRequeridoException
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Table(name = "PRODUCTOS")
@Entity
class ProductoDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val idProducto: UUID?,
    val nombre: String?,
    val sku: String?,
    val descripcion: String?,
    val precioSinDescuento: Double?,
    val tieneDescuento: Boolean?
) {
    constructor(idProducto: UUID?) : this(
        idProducto = idProducto,
        nombre = null,
        sku = null,
        descripcion = null,
        precioSinDescuento = null,
        tieneDescuento = null
    )

    constructor(
        nombre: String?,
        sku: String?,
        descripcion: String?,
        precioSinDescuento: Double?,
        tieneDescuento: Boolean?
    ) : this(
        idProducto = null,
        nombre = nombre,
        sku = sku,
        descripcion = descripcion,
        precioSinDescuento = precioSinDescuento,
        tieneDescuento = tieneDescuento
    )
}

fun ProductoDto.toProducto(): Producto =
    if (tieneDescuento == true)
        ProductoDescuento(
            idProducto = idProducto ?: throw CampoRequeridoException("id_producto"),
            nombre = nombre,
            sku = sku,
            descripcion = descripcion,
            precioSinDescuento = precioSinDescuento ?: throw CampoRequeridoException("precio_sin_descuento")
        )
    else
        ProductoSimple(
            idProducto = idProducto ?: throw CampoRequeridoException("id_producto"),
            nombre = nombre,
            sku = sku,
            descripcion = descripcion,
            precioSinDescuento = precioSinDescuento ?: throw CampoRequeridoException("precio_sin_descuento")
        )