package com.example.ezcart.domain.usecase

import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.repository.cart.CartRepository
import javax.inject.Inject

class GetCartListFromDBUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun getCartItems(): List<Cart> = cartRepository.getCartItems()
}