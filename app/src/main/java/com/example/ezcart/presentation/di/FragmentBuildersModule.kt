package com.example.ezcart.presentation.di


import com.example.ezcart.presentation.cart.CartFragment
import com.example.ezcart.presentation.home_screen.HomeScreenFragment
import com.example.ezcart.presentation.login.LoginFragment
import com.example.ezcart.presentation.orders.OrdersFragment
import com.example.ezcart.presentation.produc_details.ProductDetailsFragment
import com.example.ezcart.presentation.rate_us.RateUsFragment
import com.example.ezcart.presentation.registration.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragmentInjector(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeScreenFragmentInjector(): HomeScreenFragment

    @ContributesAndroidInjector
    abstract fun contributeProductDetailsFragmentInjector(): ProductDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeCartFragmentInjector(): CartFragment

    @ContributesAndroidInjector
    abstract fun contributeRateUsFragment(): RateUsFragment

    @ContributesAndroidInjector
    abstract fun contributeOrdersFragment(): OrdersFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment
}
