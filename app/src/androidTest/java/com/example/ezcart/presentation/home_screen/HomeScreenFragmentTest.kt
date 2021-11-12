package com.example.ezcart.presentation.home_screen

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ezcart.*
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.event.OpenProductDetailsEvent
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class HomeScreenFragmentTest: TestCase()  {

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: HomeScreenViewModel

    private val results = MutableLiveData<List<ProductDetails>>()

    @Before
    fun init() {
        viewModel = Mockito.mock(HomeScreenViewModel::class.java)
        whenever(viewModel.productList).thenReturn(results)

        val scenario = launchFragmentInContainer(themeResId = R.style.AppTheme) {
            HomeScreenFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)
    }

    @Test
    fun loadProducts() {
        val productDetails = arrayListOf(MockTestUtil.mockProducts())
        results.postValue(productDetails)

        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText("itemName"))))
    }

    @Test
    fun testRecyclerViewClick() {
        val productDetails = MockTestUtil.mockProducts()
        val ProductDetailsList = arrayListOf(productDetails)
        results.postValue(ProductDetailsList)
        val event =  OpenProductDetailsEvent(MockTestUtil.mockProducts())

        onView(listMatcher().atPosition(0)).perform(click())

        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText(event.productDetails.item_name))))

    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.productList)
    }
}