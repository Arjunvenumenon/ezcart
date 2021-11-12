package com.example.ezcart.presentation.produc_details

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.databinding.FragmentProductDetailsBinding
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.util.DOLLAR_SYMBOL
import com.example.ezcart.util.PRODUCT
import com.example.ezcart.util.getDate
import com.example.ezcart.util.showToast
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ProductDetailsFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var productDetails: ProductDetails

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: ProductDetailsViewModel by viewModels {
        viewModelFactory
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backToHomeScreen()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        arguments?.let {
            productDetails = it.getSerializable(PRODUCT) as ProductDetails
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        setHeaderImage()

        binding.addToCart.setOnClickListener{
            addItemToCart()
        }

        return binding.root
    }

    private fun addItemToCart() {
        lifecycleScope.launch{
            viewModel.addItemToCart(productDetails)
            showToast(requireContext(),"${productDetails.item_name} added to Cart")
            backToHomeScreen()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setHeaderImage() {
        val imgSuffix = "_banner"
        val imageName = productDetails.item_name.lowercase(Locale.getDefault())
            .replace("\\s".toRegex(), "")+imgSuffix
        val resources: Resources = requireContext().resources
        val resourceId: Int = resources.getIdentifier(
            imageName, "drawable", requireContext().packageName
        )
        binding.prdImage.setImageResource(resourceId)
        binding.prdAmount.text = "$DOLLAR_SYMBOL ${productDetails.price}"
        binding.prdDelivery.text = "Free Delivery"
        binding.prdDeliveryDate.text = "Delivery by ${getDate()}"
        binding.prdSoldBy.text = "Sold By :"
    }

    private fun backToHomeScreen() {
        onBackPressedCallback.remove()
        requireActivity().onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback.remove()
    }


}