package com.example.ezcart.presentation.produc_details

import androidx.lifecycle.ViewModel
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.AddItemToCartUseCase
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor(
    private val addItemToCartUseCase: AddItemToCartUseCase
): ViewModel()  {

    suspend fun addItemToCart(productDetails: ProductDetails) {
        addItemToCartUseCase.execute(productDetails)
    }

}

