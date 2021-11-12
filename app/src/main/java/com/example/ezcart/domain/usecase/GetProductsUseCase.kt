package com.example.ezcart.domain.usecase

import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.data.repository.login.LoginRepositoryImpl
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val loginRepository: LoginRepositoryImpl) {

    suspend fun execute(): List<ProductDetails>? = loginRepository.getProductsFromApi()
}