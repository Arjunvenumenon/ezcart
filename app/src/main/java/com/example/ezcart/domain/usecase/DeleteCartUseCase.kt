package com.example.ezcart.domain.usecase

import com.example.ezcart.data.repository.cart.CartRepository
import javax.inject.Inject

class DeleteCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    suspend fun deleteSingleItem(itemName: String) = cartRepository.deleteItemFromCart(itemName)

}