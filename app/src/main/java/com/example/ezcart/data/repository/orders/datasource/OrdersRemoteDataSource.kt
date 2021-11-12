package com.example.ezcart.data.repository.orders.datasource

import com.example.ezcart.data.api.RetrofitService
import com.example.ezcart.data.model.OrderResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class OrdersRemoteDataSource @Inject constructor(private val retrofitService: RetrofitService){

    fun getOrdersFromServer(): Observable<OrderResponse> = retrofitService.getOrdersFromServer()
}