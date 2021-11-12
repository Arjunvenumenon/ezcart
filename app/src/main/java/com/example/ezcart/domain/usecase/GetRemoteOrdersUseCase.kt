package com.example.ezcart.domain.usecase

import com.example.ezcart.data.model.OrderResponse
import com.example.ezcart.data.model.RemoteOrderHead
import com.example.ezcart.data.repository.orders.OrdersRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetRemoteOrdersUseCase @Inject constructor(private val ordersRepository: OrdersRepository) {

    fun execute(): Observable<OrderResponse> = ordersRepository.getOrdersFromServer()
}