package com.challenge.msecommerce.domain.dao

import com.challenge.msecommerce.domain.model.dto.ProductoDto
import org.springframework.data.repository.CrudRepository
import java.util.*

interface ProductoRepository : CrudRepository<ProductoDto, UUID> {
}