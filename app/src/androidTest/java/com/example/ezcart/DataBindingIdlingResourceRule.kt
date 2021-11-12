package com.example.ezcart

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class DataBindingIdlingResourceRule : TestWatcher() {
    private val idlingResource = DataBindingIdlingResource()
    
    fun monitorFragment(fragmentScenario: FragmentScenario<out Fragment>) {
        idlingResource.monitorFragment(fragmentScenario)
    }

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

}