package com.challenge.msecommerce.service

import com.challenge.msecommerce.domain.dao.CarritoItemRepository
import com.challenge.msecommerce.domain.dao.CarritoRepository
import com.challenge.msecommerce.domain.model.dto.*
import com.challenge.msecommerce.domain.model.entities.Carrito
import com.challenge.msecommerce.domain.model.entities.EnumEstadoCarrito
import com.challenge.msecommerce.exceptions.CarritoNoEncontradoException
import com.challenge.msecommerce.exceptions.EstadoCarritoInvalidoException
import com.challenge.msecommerce.exceptions.ItemCarritoNoEncontradoException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CarritosServiceImpl(
    val carritoRepository: CarritoRepository,
    val carritoItemRepository: CarritoItemRepository
) : ICarritosService {

    override fun obtenerCarrito(idCarrito: UUID): Carrito =
        encontrarCarritoPorId(idCarrito).let {
            it.toCarrito()
        }

    @Transactional
    override fun crearCarrito(carritoRequest: CrearCarritoRequestDto): UUID? =
        carritoRepository.save(
            CarritoDto(
                estado = EnumEstadoCarrito.PENDIENTE
            )
        ).let { carritoCreado ->
            carritoRequest.itemsCarrito.forEach {
                carritoItemRepository.save(it.toCarritoItemDto(carritoCreado))
            }
            carritoCreado.idCarrito
        }

    @Transactional
    override fun agregarItemsAlCarrito(modificarCarritoRequest: AgregarItemsCarritoRequestDto): UUID? =
        encontrarCarritoPorId(modificarCarritoRequest.idCarrito)
            .let { carritoEncontrado ->
                when (carritoEncontrado.estado) {
                    EnumEstadoCarrito.PENDIENTE -> insertarNuevosItems(
                        modificarCarritoRequest.itemsCarrito,
                        carritoEncontrado
                    )
                    EnumEstadoCarrito.COMPLETADO -> throw EstadoCarritoInvalidoException(
                        carritoEncontrado.idCarrito,
                        carritoEncontrado.estado
                    )
                }
                carritoEncontrado.idCarrito
            }

    override fun checkout(idCarrito: UUID): Carrito =
        encontrarCarritoPorId(idCarrito)
            .let {
                carritoRepository.save(it.terminarCarrito())
            }.let {
                it.toCarrito()
            }

    override fun borrarItemDelCarrito(idCarritoItem: UUID) =
        carritoItemRepository.findById(idCarritoItem)
            .orElseThrow {
                throw ItemCarritoNoEncontradoException(idCarritoItem)
            }
            .let {
                when (it.carritoDto?.estado) {
                    EnumEstadoCarrito.PENDIENTE -> carritoItemRepository.deleteById(idCarritoItem)
                    EnumEstadoCarrito.COMPLETADO -> throw EstadoCarritoInvalidoException(
                        it.carritoDto.idCarrito,
                        it.carritoDto.estado
                    )
                }
            }

    private fun encontrarCarritoPorId(idCarrito: UUID) =
        carritoRepository.findById(idCarrito)
            .orElseThrow {
                throw CarritoNoEncontradoException(idCarrito)
            }

    private fun insertarNuevosItems(itemsCarrito: List<CarritoItemRequestDto>, carritoEncontrado: CarritoDto?) {
        itemsCarrito.forEach {
            carritoItemRepository.save(it.toCarritoItemDto(carritoEncontrado))
        }
    }

}