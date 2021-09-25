package com.challenge.msecommerce.domain.dao

import com.challenge.msecommerce.domain.model.dto.CarritoDto
import com.challenge.msecommerce.domain.model.dto.CarritoItemDto
import com.challenge.msecommerce.domain.model.dto.ProductoDto
import org.springframework.data.repository.CrudRepository
import java.util.*

interface CarritoItemRepository : CrudRepository<CarritoItemDto, UUID> {
}