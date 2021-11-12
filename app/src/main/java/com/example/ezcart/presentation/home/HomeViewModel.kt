package com.example.ezcart.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.GetProductsFromDBUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getProductsFromDBUseCase: GetProductsFromDBUseCase
): ViewModel() {

    val productList = MutableLiveData<List<ProductDetails>>(listOf())

    suspend fun getProducts() {

        productList.value = getProductsFromDBUseCase.execute()
    }


}