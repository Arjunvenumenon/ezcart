package com.example.ezcart.presentation.cart

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.platform.app.ActivityLifecycleTimeout
import androidx.test.rule.ActivityTestRule
import com.example.ezcart.*
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.presentation.MainActivity
import com.example.ezcart.presentation.home_screen.HomeScreenFragment
import com.example.ezcart.util.cart
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito




@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CartFragmentTest: TestCase() {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRules()


    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()

    private lateinit var viewModel: CartViewModel

    private val results = MutableLiveData<List<Cart>>()

    private lateinit var fragment: FragmentActivity

    @Before
    fun init() {
        viewModel = Mockito.mock(CartViewModel::class.java)
        whenever(viewModel.cartList).thenReturn(results)

        val scenario = launchFragmentInContainer(themeResId = R.style.AppTheme) {
            CartFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
                fragment = activityTestRule.activity
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)

    }

    @Test
    fun getCartList() {
        val cartList = arrayListOf(MockTestUtil.mockCart())
        results.postValue(cartList)

        onView(listMatcher().atPosition(0))
            .check(matches(hasDescendant(withText(cartList[0].item_name))))

    }


    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.cartList)
    }

}