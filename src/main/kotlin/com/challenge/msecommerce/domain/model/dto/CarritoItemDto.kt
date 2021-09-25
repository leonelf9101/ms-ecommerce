package com.challenge.msecommerce.domain.model.dto

import com.challenge.msecommerce.domain.model.entities.CarritoItem
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
@Table(name = "CARRITOS_ITEMS")
@Entity
class CarritoItemDto(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
        val idCarritoItem: UUID?,
    @ManyToOne
    @JoinColumn(name="id_carrito", nullable=false)
    val carritoDto: CarritoDto?,
    @ManyToOne
    @JoinColumn(name="id_producto", nullable=false)
    val productoDto: ProductoDto,
    val cantidad: Integer
) {
    constructor(carritoDto: CarritoDto?, productoDto: ProductoDto, cantidad : Integer):
            this(
                idCarritoItem = null,
                carritoDto = carritoDto,
                productoDto = productoDto,
                cantidad = cantidad
            )
}

fun CarritoItemDto.toCarritoItem() =
    CarritoItem(
        idCarritoItem,
        productoDto.toProducto(),
        cantidad
    )