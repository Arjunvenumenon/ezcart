package com.example.ezcart.presentation.home_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.GetProductsFromDBUseCase
import com.example.ezcart.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class HomeScreenViewModel @Inject constructor(
    private val getProductsFromDBUseCase: GetProductsFromDBUseCase
): ViewModel() {

    val productList = MutableLiveData<List<ProductDetails>>(listOf())

    suspend fun getProducts() {

        productList.value = getProductsFromDBUseCase.execute()
    }


}