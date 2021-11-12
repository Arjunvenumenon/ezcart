package com.example.ezcart.presentation.login

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ezcart.*
import com.example.ezcart.DataBindingIdlingResourceRule
import com.example.ezcart.TaskExecutorWithIdlingResourceRule
import com.example.ezcart.ViewModelUtil
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest: TestCase()  {


    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()


    private lateinit var viewModel: LoginViewModel


    @Before
    fun init() {

        viewModel = Mockito.mock(LoginViewModel::class.java)

        val scenario = launchFragmentInContainer(themeResId = R.style.AppTheme) {
            LoginFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)


    }

    @Test
    fun testLogin() {
        val userId = "testing@gmail.com"
        val password = "Name@1234"

        onView(withId(R.id.loginEditTextUserId)).perform(typeText(userId), closeSoftKeyboard())
        onView(withId(R.id.loginEditTextPassword)).perform(typeText(password), closeSoftKeyboard())

        //onView(withId(R.id.buttonLogin)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.buttonLogin)).perform(click())

        verify(viewModel).getProducts()

    }
}