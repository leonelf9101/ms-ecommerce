package com.challenge.msecommerce.domain.model.dto

import com.challenge.msecommerce.domain.model.entities.Carrito
import com.challenge.msecommerce.domain.model.entities.EnumEstadoCarrito
import com.challenge.msecommerce.domain.model.entities.Producto
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Table(name = "CARRITOS")
@Entity
class CarritoDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    val idCarrito: UUID?,
    val estado: EnumEstadoCarrito,

    @OneToMany(mappedBy="carritoDto")
    val itemsCarrito: List<CarritoItemDto>?
) {
    constructor(estado: EnumEstadoCarrito):
            this(
                idCarrito = null,
                itemsCarrito = null,
                estado = estado
            )
}

fun CarritoDto.terminarCarrito() =
    CarritoDto(
        idCarrito = idCarrito,
        estado = EnumEstadoCarrito.COMPLETADO,
        itemsCarrito = itemsCarrito
    )

fun CarritoDto.toCarrito() =
    Carrito(
        idCarrito = idCarrito,
        estado = estado,
        itemsCarrito = itemsCarrito?.map{ it.toCarritoItem() }
    )