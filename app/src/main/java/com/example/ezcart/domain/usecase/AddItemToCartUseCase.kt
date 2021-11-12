package com.example.ezcart.domain.usecase

import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.data.repository.product_details.ProductDetailsRepository
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(val productDetailsRepository: ProductDetailsRepository) {

    suspend fun execute(productDetails: ProductDetails) = productDetailsRepository.addToCart(productDetails)
}