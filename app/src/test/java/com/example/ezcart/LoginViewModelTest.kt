package com.example.ezcart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.GetProductsUseCase
import com.example.ezcart.presentation.login.LoginViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class LoginViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var getProductsUseCase: GetProductsUseCase

    private lateinit var viewModel: LoginViewModel

    private var testSingle: Single<List<ProductDetails>>? = null

    @Test
    fun getProductsTest() {

        val products = ProductDetails("itemName","desc",2,"currency",
            "date","qty")
        val productList = arrayListOf(products)

        testSingle = Single.just(productList)

        viewModel = LoginViewModel(getProductsUseCase)

        coroutinesTestRule.runBlockingTest {
            whenever(getProductsUseCase.execute()).thenReturn(productList)

            viewModel.getProducts().observeForever {
                assertEquals(1, it?.size)
            }
        }

    }
}