package com.challenge.msecommerce.service

import com.challenge.msecommerce.domain.dao.ProductoRepository
import com.challenge.msecommerce.domain.model.dto.*
import com.challenge.msecommerce.domain.model.entities.Producto
import com.challenge.msecommerce.exceptions.ProductoNoEncontradoException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductosServiceImpl(
    val productoRepository: ProductoRepository
) : IProductosService {

    override fun obtenerProductos(): List<Producto> =
        productoRepository.findAll().map {
            it.toProducto()
        }

    override fun crearProducto(productoCreado: CrearProductoRequestDto): Producto =
        productoRepository.save(
            productoCreado.toProductoDto()
        ).let { productoInsertado ->
            productoInsertado.toProducto()
        }

    override fun modificarProducto(productoModificado: ModificarProductoRequestDto): Producto =
        encontrarProductoPorId(productoModificado.idProducto)
            .let {
                productoRepository.save(
                    productoModificado.toProductoDto()
                )
            }.let { productoInsertado ->
                productoInsertado.toProducto()
            }

    override fun borrarProducto(idProducto: UUID) =
            productoRepository.deleteById(
                idProducto
            )


    private fun encontrarProductoPorId(idProducto: UUID) =
        productoRepository.findById(idProducto)
            .orElseThrow {
                throw ProductoNoEncontradoException(idProducto)
            }




}