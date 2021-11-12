package com.example.ezcart.presentation.orders

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezcart.data.model.*
import com.example.ezcart.domain.usecase.GetOrdersFromDBUseCase
import com.example.ezcart.domain.usecase.GetRemoteOrdersUseCase
import com.example.ezcart.domain.usecase.SaveRemoteOrdersUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
    private val getRemoteOrdersUseCase: GetRemoteOrdersUseCase,
    private val saveRemoteOrdersUseCase: SaveRemoteOrdersUseCase,
    private val getOrdersFromDBUseCase: GetOrdersFromDBUseCase
): ViewModel() {

    private lateinit var subscription: Disposable
    private lateinit var apiResponseState: ApiResponseState
    val displayList = MutableLiveData<List<OrderDisplayModel>>(listOf())
    val state = MutableStateFlow(ApiResponseState(ApiStatus.READY))


    fun getRemoteOrders() {
        apiResponseState = state.value.copy()

        apiResponseState.apiStatus = ApiStatus.PROGRESS
        state.value = apiResponseState.copy()

        subscription = getRemoteOrdersUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { onRetrieveOrdersListFinish() }
            .subscribe(
                { result -> onRetrieveOrdersListSuccess(result) },
                { onRetrieveOrdersListError() }
            )

    }

    suspend fun getOrdersFromDB() {
        val returnList = mutableListOf<OrderDisplayModel>()
        displayList.value = listOf()

        val ret = getOrdersFromDBUseCase.execute()
        ret.forEach { orderHeadList ->
            val date = orderHeadList.order_date
                orderHeadList.data.forEach{
                    val model = OrderDisplayModel(
                        productName = it.item_name,
                        date = date,
                        amount = it.price
                    )
                    returnList.add(model)
                }
            }

        displayList.value = returnList
    }

    private fun onRetrieveOrdersListFinish(){
        apiResponseState.apiStatus = ApiStatus.FINISHED
        state.value = apiResponseState.copy()
    }

    private fun onRetrieveOrdersListSuccess(orderResponse: OrderResponse){
        viewModelScope.launch {
            saveRemoteOrdersUseCase.execute(orderResponse.data)
            apiResponseState.apiStatus = ApiStatus.DATA_SAVED
            state.value = apiResponseState.copy()
        }
    }

    private fun onRetrieveOrdersListError(){
        apiResponseState.apiStatus = ApiStatus.ERROR
        state.value = apiResponseState.copy()

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}