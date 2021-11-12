package com.example.ezcart.domain.usecase

import com.example.ezcart.data.model.RemoteOrderHead
import com.example.ezcart.data.repository.orders.OrdersRepository
import javax.inject.Inject

class SaveRemoteOrdersUseCase @Inject constructor(private val ordersRepository: OrdersRepository) {

    suspend fun execute(orderList: List<RemoteOrderHead>) = ordersRepository.saveOrdersToDB(orderList)

}