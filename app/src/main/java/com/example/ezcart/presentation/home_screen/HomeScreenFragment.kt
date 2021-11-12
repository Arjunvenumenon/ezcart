package com.example.ezcart.presentation.home_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.databinding.FragmentHomeScreenBinding
import com.example.ezcart.event.OpenProductDetailsEvent
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.presentation.home_screen.adapter.ProductListAdapter
import com.example.ezcart.presentation.home_screen.adapter.ImageSliderAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class HomeScreenFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentHomeScreenBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeScreenViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        productListAdapter = ProductListAdapter(mutableListOf(),requireContext(),
            ::openProductDetailsScreen)
        binding.productList.layoutManager = LinearLayoutManager(requireContext())
        binding.productList.adapter = productListAdapter

        getProduct()
        imageSliderImplementation()

        viewModel.productList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        return binding.root
    }


    private fun getProduct() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getProducts()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter(list: List<ProductDetails>) {
        productListAdapter.list.clear()
        productListAdapter.list.addAll(list)
        productListAdapter.notifyDataSetChanged()

    }

    private fun imageSliderImplementation() {
        val adapter = ImageSliderAdapter(requireContext())
        binding.viewpager.adapter = adapter
        binding.circleIndiactor.setViewPager(binding.viewpager)
    }

    private fun openProductDetailsScreen(productDetails: ProductDetails) {
        EventBus.getDefault().post(OpenProductDetailsEvent(productDetails))
    }

}