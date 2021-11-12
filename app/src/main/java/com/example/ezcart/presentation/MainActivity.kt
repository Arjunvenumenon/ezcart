package com.example.ezcart.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ezcart.R
import com.example.ezcart.databinding.ActivityMainBinding
import com.example.ezcart.presentation.dialog.CommonDialog
import com.example.ezcart.presentation.registration.RegistrationFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var binding: ActivityMainBinding


    private val SELECTED_ITEM_POSITION = "ItemPosition"
    private var mPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            openLoginPage()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of item position
        outState.putInt(SELECTED_ITEM_POSITION, mPosition)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // Read the state of item position
        mPosition = savedInstanceState.getInt(SELECTED_ITEM_POSITION)
    }

    private fun openLoginPage() {
        lifecycleScope.launch {
            delay(1500)
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val currentFragment = navHost?.childFragmentManager?.fragments?.get(0)
        if (supportFragmentManager.backStackEntryCount > 0 || currentFragment is RegistrationFragment) {
            super.onBackPressed()
        } else {
            CommonDialog(this, getString(R.string.exit_application),false) {
                if (it) {
                    super.onBackPressed()
                }
            }.show()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector


}