package com.example.ezcart.presentation.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.databinding.FragmentCartBinding
import com.example.ezcart.event.SetCartCountEvent
import com.example.ezcart.presentation.cart.adapter.CartAdapter
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.util.DOLLAR_SYMBOL
import com.example.ezcart.util.showToast
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CartFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartList: List<Cart>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CartViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        cartAdapter = CartAdapter(mutableListOf(), requireContext(),
                            ::deleteItem)
        binding.cartList.layoutManager = LinearLayoutManager(requireContext())
        binding.cartList.adapter = cartAdapter

        getCartList()

        viewModel.cartList.observe(viewLifecycleOwner){
            manageState(it)
        }

        binding.placeOrder.clicks()
            .throttleFirst(100, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                placeOrder()
            }

        return binding.root
    }

    private fun getCartList() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getCartItems()
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun manageState(list: List<Cart>) {

        EventBus.getDefault().post(SetCartCountEvent(list.size))
        cartList = list
        cartAdapter.list.clear()
        cartAdapter.list.addAll(list)
        cartAdapter.notifyDataSetChanged()

        val total = list.sumOf { it.price ?: 0 }
        binding.totalAmount.text = "$DOLLAR_SYMBOL $total"

        binding.placeOrder.isEnabled = list.isNotEmpty()
    }

    private fun deleteItem(itemName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.deleteCartItem(itemName)
        }
    }

    private fun placeOrder(){
        lifecycleScope.launch {
            viewModel.placeOrder(cartList)
            showToast(requireContext(),"Order Placed")
            backToHomeScreen()
        }
    }

    private fun backToHomeScreen() {
        requireActivity().onBackPressed()
    }


}