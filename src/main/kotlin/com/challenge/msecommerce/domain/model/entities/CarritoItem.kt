package com.challenge.msecommerce.domain.model.entities

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.*

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class CarritoItem(
    val idCarritoItem: UUID?,
    val producto: Producto,
    val cantidad: Integer
)