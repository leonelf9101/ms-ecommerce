package com.challenge.msecommerce.exceptions

import com.challenge.msecommerce.domain.model.dto.ErrorResponseDto
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ProductoNoEncontradoException::class)
    fun unknownTenantErrorHandler(e: ProductoNoEncontradoException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("PRODUCTO_NO_ENCONTRADO", "El producto con id ${e.idProducto} no fue encontrado"),
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(CampoRequeridoException::class)
    fun unknownTenantErrorHandler(e: CampoRequeridoException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("CAMPO_REQUERIDO_NULO", "El campo ${e.campoRequerido} es requerido"),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(CarritoNoEncontradoException::class)
    fun unknownTenantErrorHandler(e: CarritoNoEncontradoException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("CARRITO_NO_ENCONTRADO", "El carrito con id ${e.idCarrito} no fue encontrado"),
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(EstadoCarritoInvalidoException::class)
    fun unknownTenantErrorHandler(e: EstadoCarritoInvalidoException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("ESTADO_CARRITO_INVALIDO", "El carrito con id ${e.idCarrito} se encuentra en un estado invalido (${e.estado.name})"),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(ItemCarritoNoEncontradoException::class)
    fun unknownTenantErrorHandler(e: ItemCarritoNoEncontradoException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("ITEM_NO_ENCONTRADO", "El item con id ${e.idItemCarrito} no fue encontrado"),
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun unknownTenantErrorHandler(e: EmptyResultDataAccessException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("REGISTRO_NO_ENCONTRADO", "No se encontraron datos para la solicitud"),
            HttpStatus.NOT_FOUND
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun unknownTenantErrorHandler(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("SOLICITUD_INVALIDA", "La solicitud enviada es invalida"),
            HttpStatus.BAD_REQUEST
        )

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun unknownTenantErrorHandler(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponseDto> =
        ResponseEntity<ErrorResponseDto>(
            ErrorResponseDto("ARGUMENTO_INVALIDO", "El argumento enviado es invalido"),
            HttpStatus.BAD_REQUEST
        )



}