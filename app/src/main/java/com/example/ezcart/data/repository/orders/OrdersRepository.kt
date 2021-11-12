package com.example.ezcart.data.repository.orders

import com.example.ezcart.data.model.OrderResponse
import com.example.ezcart.data.model.RemoteOrderHead
import com.example.ezcart.data.repository.orders.datasource.OrdersLocalDataSource
import com.example.ezcart.data.repository.orders.datasource.OrdersRemoteDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val ordersRemoteDataSource: OrdersRemoteDataSource,
    private val ordersLocalDataSource: OrdersLocalDataSource
) {

    fun getOrdersFromServer(): Observable<OrderResponse> {
        return ordersRemoteDataSource.getOrdersFromServer()
    }

    suspend fun saveOrdersToDB(orderList: List<RemoteOrderHead>) {
        ordersLocalDataSource.saveOrderToDB(orderList)
    }

    suspend fun getOrderListFromDB(): List<RemoteOrderHead> {
        return ordersLocalDataSource.getOrderList()
    }

}