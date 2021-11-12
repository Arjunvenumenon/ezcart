package com.example.ezcart.presentation.registration

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.example.ezcart.*
import com.example.ezcart.DataBindingIdlingResourceRule
import com.example.ezcart.TaskExecutorWithIdlingResourceRule
import com.example.ezcart.ViewModelUtil
import com.example.ezcart.data.db.entities.UserProfile
import com.example.ezcart.presentation.MainActivity
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@LargeTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RegistrationFragmentTest: TestCase()  {

    @get:Rule
    val coroutinesTestRule = CoroutineTestRules()

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule()


    private lateinit var viewModel: RegistrationViewModel

    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Before
    fun init() {

        viewModel = Mockito.mock(RegistrationViewModel::class.java)

        val scenario = launchFragmentInContainer(themeResId = R.style.AppTheme) {
            RegistrationFragment().apply {
                viewModelFactory = ViewModelUtil.createFor(viewModel)
                activityScenarioRule.scenario
            }
        }
        dataBindingIdlingResourceRule.monitorFragment(scenario)

        runOnUiThread {
            navController.setGraph(R.navigation.nav_graph)
        }

        runOnUiThread {
            navController.setCurrentDestination(R.id.registrationFragment)
        }

        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @LargeTest
    @Test
    fun saveUser() {

        val firstName = "First"
        val lastName = "last"
        val userId = "userid@gmail.com"
        val password= "password"
        val mobile = "9876543210"

        val userProfile = UserProfile(
            firstName = firstName,
            lastName = lastName,
            userId = userId,
            password = password,
            mobileNo = mobile
        )

        onView(withId(R.id.editTextFirstName)).perform(typeText(firstName), closeSoftKeyboard())
        onView(withId(R.id.editTextLastName)).perform(typeText(lastName), closeSoftKeyboard())
        onView(withId(R.id.editTextUserId)).perform(typeText(userId), closeSoftKeyboard())
        onView(withId(R.id.editTextPassword)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.editTextMobile)).perform(typeText(mobile), closeSoftKeyboard())

        onView(withId(R.id.buttonRegister)).perform(click())

        coroutinesTestRule.runBlockingTest {
            verify(viewModel).clearUser()
            verify(viewModel).saveUser(userProfile)
        }
    }
}