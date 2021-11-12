package com.example.ezcart.presentation.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.domain.usecase.DeleteCartUseCase
import com.example.ezcart.domain.usecase.GetCartListFromDBUseCase
import com.example.ezcart.domain.usecase.PlaceOrderUseCase
import com.example.ezcart.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class CartViewModel @Inject constructor(
    private val getCartListFromDBUseCase: GetCartListFromDBUseCase,
    private val deleteCartUseCase: DeleteCartUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase
): ViewModel() {

    val cartList = MutableLiveData<List<Cart>>(listOf())

    suspend fun getCartItems() {
        cartList.value = getCartListFromDBUseCase.getCartItems()
    }

    suspend fun deleteCartItem(itemName: String) {
        deleteCartUseCase.deleteSingleItem(itemName)
        cartList.value = getCartListFromDBUseCase.getCartItems()
    }

    suspend fun placeOrder(cartList: List<Cart>) {
        placeOrderUseCase.placeOrder(cartList)
    }

}