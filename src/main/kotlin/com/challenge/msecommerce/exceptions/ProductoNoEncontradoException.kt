package com.challenge.msecommerce.exceptions

import java.util.*

class ProductoNoEncontradoException (val idProducto: UUID): RuntimeException()