package com.example.ezcart.domain.usecase

import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.repository.cart.CartRepository
import javax.inject.Inject

class GetCartCountUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun execute(): Int = cartRepository.getCartCount()
}