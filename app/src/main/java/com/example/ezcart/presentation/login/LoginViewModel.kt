package com.example.ezcart.presentation.login

import androidx.lifecycle.*
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.GetProductsUseCase
import com.example.ezcart.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class LoginViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
): ViewModel() {

    fun getProducts() = liveData {

        val productList: List<ProductDetails>? = getProductsUseCase.execute()
        emit(productList)
    }
}