package com.example.ezcart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.domain.usecase.DeleteCartUseCase
import com.example.ezcart.domain.usecase.GetCartListFromDBUseCase
import com.example.ezcart.domain.usecase.PlaceOrderUseCase
import com.example.ezcart.presentation.cart.CartViewModel
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(MockitoJUnitRunner::class)
class CartViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var getCartListFromDBUseCase: GetCartListFromDBUseCase

    @Mock
    lateinit var deleteCartUseCase: DeleteCartUseCase

    @Mock
    lateinit var placeOrderUseCase: PlaceOrderUseCase

    lateinit var viewModel: CartViewModel

    @Test
    fun getCartItemsTest() {
        val cartItems =  Cart("name","desc",0, "currency",
            "10-07-2021","5")
        val cartList = arrayListOf(cartItems)

        viewModel = CartViewModel(getCartListFromDBUseCase,deleteCartUseCase,placeOrderUseCase)

        coroutinesTestRule.runBlockingTest {
            whenever(getCartListFromDBUseCase.getCartItems()).thenReturn(cartList)

            viewModel.getCartItems()

            Assert.assertEquals(1, viewModel.cartList.value?.size)
        }

    }
}