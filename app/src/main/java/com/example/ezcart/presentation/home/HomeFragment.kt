package com.example.ezcart.presentation.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.ezcart.R
import com.example.ezcart.data.model.ScreenState
import com.example.ezcart.databinding.FragmentHomeBinding
import com.example.ezcart.event.OpenProductDetailsEvent
import com.example.ezcart.event.SetCartCountEvent
import com.example.ezcart.presentation.cart.CartFragment
import com.example.ezcart.presentation.dialog.CommonDialog
import com.example.ezcart.presentation.help.HelpFragment
import com.example.ezcart.presentation.home_screen.HomeScreenFragment
import com.example.ezcart.presentation.orders.OrdersFragment
import com.example.ezcart.presentation.produc_details.ProductDetailsFragment
import com.example.ezcart.presentation.rate_us.RateUsFragment
import com.example.ezcart.util.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeFragment : Fragment(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().supportFragmentManager.addOnBackStackChangedListener(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        EventBus.getDefault().register(this)

        if(savedInstanceState == null) {
            openHomeScreen()
        }

        binding.nav.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.viewCart -> {
                    openCartFragment()
                    closeDrawer()
                }

                R.id.feedback -> {
                    openRateUsFragment()
                    closeDrawer()
                }

                R.id.help -> {
                    openHelpFragment()
                    closeDrawer()
                }

                R.id.yourOrders -> {
                    openOrdersFragment()
                    closeDrawer()
                }

                R.id.logout -> {
                    logout()
                    closeDrawer()
                }
            }

            true
        }

        binding.navButton.setOnClickListener {
            binding.drawerLayout.open()
        }

        binding.backButton.setOnClickListener {
            onBackButtonPressed()
        }

        return binding.root
    }


    private fun openHomeScreen() {
        requireActivity().supportFragmentManager.commit {
            replace(binding.container.id, HomeScreenFragment::class.java, bundleOf())
        }
    }

    private fun openCartFragment() {
        binding.headerLogo.visibility = View.GONE
        binding.navButton.visibility = View.GONE
        binding.title.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.cartCount.visibility = View.VISIBLE
        binding.title.text = cart
        requireActivity().supportFragmentManager.commit {
            replace(binding.container.id, CartFragment::class.java, bundleOf())
            addToBackStack(null)
        }
    }

    private fun openRateUsFragment() {
        binding.headerLogo.visibility = View.GONE
        binding.navButton.visibility = View.GONE
        binding.title.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.title.text = rate_us
        requireActivity().supportFragmentManager.commit {
            replace(binding.container.id, RateUsFragment::class.java, bundleOf())
            addToBackStack(null)
        }
    }

    private fun openHelpFragment() {
        binding.headerLogo.visibility = View.GONE
        binding.navButton.visibility = View.GONE
        binding.title.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.title.text = help
        requireActivity().supportFragmentManager.commit {
            replace(binding.container.id, HelpFragment::class.java, bundleOf())
            addToBackStack(null)
        }
    }

    private fun openOrdersFragment() {
        binding.headerLogo.visibility = View.GONE
        binding.navButton.visibility = View.GONE
        binding.title.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.title.text = orders
        requireActivity().supportFragmentManager.commit {
            replace(binding.container.id, OrdersFragment::class.java, bundleOf())
            addToBackStack(null)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun openProductDetails(event: OpenProductDetailsEvent) {
        binding.headerLogo.visibility = View.GONE
        binding.navButton.visibility = View.GONE
        binding.title.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.title.text = event.productDetails.item_name
        requireActivity().supportFragmentManager.commit {
            replace(
                    binding.container.id, ProductDetailsFragment::class.java, bundleOf(
                PRODUCT to event.productDetails
                )
            )
            addToBackStack(null)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun setCartCount(event: SetCartCountEvent) {
       binding.cartCount.text = event.cartCount.toString()
    }

    private fun onBackButtonPressed() {
        requireActivity().onBackPressed()
    }

    override fun onBackStackChanged() {
        Log.i("test","backstack changed")
        val currentFragment = requireActivity().supportFragmentManager
            .findFragmentById(binding.container.id)
        when (currentFragment) {
            is HomeScreenFragment -> {
                binding.title.visibility = View.GONE
                binding.backButton.visibility = View.GONE
                binding.cartCount.visibility = View.GONE
                binding.headerLogo.visibility = View.VISIBLE
                binding.navButton.visibility = View.VISIBLE
            }
        }
    }



    private fun closeDrawer() {
        binding.drawerLayout.post { binding.drawerLayout.close() }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("test","orientation changed")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val saveState = ScreenState(
            headerLogoState = binding.headerLogo.visibility,
            navButtonState = binding.navButton.visibility,
            titleState = binding.title.visibility,
            backButtonState = binding.backButton.visibility,
            cartCountState = binding.cartCount.visibility,
            titleValue = binding.title.text.toString()
        )
        outState.putBundle(VIEW_STATE, bundleOf(STATE_BUNDLE to  saveState))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        if (savedInstanceState != null) {
            val returnState = savedInstanceState.getBundle(VIEW_STATE)?.get(STATE_BUNDLE) as ScreenState

            binding.headerLogo.visibility = returnState.headerLogoState
            binding.navButton.visibility = returnState.navButtonState
            binding.title.visibility = returnState.titleState
            binding.backButton.visibility = returnState.backButtonState
            binding.cartCount.visibility = returnState.cartCountState
            binding.title.text = returnState.titleValue
        }

    }

    private fun logout() {
        CommonDialog(requireActivity(), getString(R.string.logout_application),false) {
            if (it) {
                val intent: Intent? =
                    requireActivity().packageManager
                        .getLaunchIntentForPackage(requireActivity().packageName)?.apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        }
                startActivity(intent)
            }
        }.show()
    }


}