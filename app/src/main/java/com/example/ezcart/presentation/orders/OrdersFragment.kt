package com.example.ezcart.presentation.orders

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
import com.example.ezcart.data.model.ApiResponseState
import com.example.ezcart.data.model.ApiStatus
import com.example.ezcart.data.model.OrderDisplayModel
import com.example.ezcart.databinding.FragmentOrdersBinding
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.presentation.orders.adapter.OrderListAdapter
import com.example.ezcart.util.isNetworkAvailable
import com.example.ezcart.util.showToast
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrdersFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentOrdersBinding
    private lateinit var subscription: Disposable
    private lateinit var orderListAdapter: OrderListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: OrdersViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOrdersBinding.inflate(inflater, container, false)

        orderListAdapter = OrderListAdapter(mutableListOf(), requireContext())
        binding.list.layoutManager = LinearLayoutManager(requireContext())
        binding.list.adapter = orderListAdapter

        if(savedInstanceState == null) {
            loadOrders()
        }

        viewModel.state
            .onEach {
                manageApiResponse(it)
            }.launchIn(lifecycleScope)

        viewModel.displayList.observe(viewLifecycleOwner) {
            setAdapter(it)
        }

        return binding.root
    }


    private fun loadOrders(){
        binding.progressbar.visibility = View.VISIBLE
        if(isNetworkAvailable(requireContext())) {
            viewModel.getRemoteOrders()
        } else {
            showToast(requireContext(),"No Connection")
        }
    }

    private fun manageApiResponse(state: ApiResponseState) {

        when(state.apiStatus) {

            ApiStatus.PROGRESS -> {
                binding.progressbar.visibility = View.VISIBLE
            }

            ApiStatus.FINISHED -> {
                binding.progressbar.visibility = View.GONE
            }

            ApiStatus.DATA_SAVED -> {
                lifecycleScope.launch {
                    getOrdersFromDbForAdapter()
                }
            }

            ApiStatus.ERROR -> {
                showToast(requireContext(),"Failed to fetch orders")
            }

            else -> {
                //Do nothing
            }
        }
    }


    private fun onRetrieveOrdersListFinish(){
        binding.progressbar.visibility = View.GONE
    }


    private fun onRetrieveOrdersListError(){
        showToast(requireContext(),"Failed to fetch orders")
    }

    private fun getOrdersFromDbForAdapter() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getOrdersFromDB()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setAdapter(list: List<OrderDisplayModel>) {
        orderListAdapter.list.clear()
        orderListAdapter.list.addAll(list)
        orderListAdapter.notifyDataSetChanged()

    }

    override fun onDestroy() {
        super.onDestroy()
        if(this::subscription.isInitialized) {
            subscription.dispose()
        }
    }

}