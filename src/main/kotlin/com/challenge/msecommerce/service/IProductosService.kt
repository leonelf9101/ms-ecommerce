package com.challenge.msecommerce.service

import com.challenge.msecommerce.domain.model.dto.CrearProductoRequestDto
import com.challenge.msecommerce.domain.model.dto.ModificarProductoRequestDto
import com.challenge.msecommerce.domain.model.entities.Producto
import java.util.*

interface IProductosService {
    fun obtenerProductos(): List<Producto>
    fun crearProducto(producto: CrearProductoRequestDto): Producto
    fun modificarProducto(producto: ModificarProductoRequestDto): Producto
    fun borrarProducto(idProducto: UUID)
}