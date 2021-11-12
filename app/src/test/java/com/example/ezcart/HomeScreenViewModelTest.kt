package com.example.ezcart

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.domain.usecase.GetProductsFromDBUseCase
import com.example.ezcart.presentation.home_screen.HomeScreenViewModel
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
class HomeScreenViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

    @Mock
    lateinit var getProductsFromDBUseCase: GetProductsFromDBUseCase


    lateinit var viewModel: HomeScreenViewModel


    @Test
    fun getProductsTest() {
        val products = ProductDetails("itemName","desc",2,"currency",
            "date","qty")
        val productList = arrayListOf(products)

        viewModel = HomeScreenViewModel(getProductsFromDBUseCase)


        coroutinesTestRule.runBlockingTest {
            whenever(getProductsFromDBUseCase.execute()).thenReturn(productList)

            viewModel.getProducts()

            Assert.assertEquals(1,viewModel.productList.value?.size)
        }
    }




}