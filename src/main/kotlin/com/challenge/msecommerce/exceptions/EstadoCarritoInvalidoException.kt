package com.challenge.msecommerce.exceptions

import com.challenge.msecommerce.domain.model.entities.EnumEstadoCarrito
import java.util.*

class EstadoCarritoInvalidoException(val idCarrito: UUID?, val estado: EnumEstadoCarrito) : RuntimeException()