package com.example.ezcart.domain.usecase

import androidx.lifecycle.LiveData
import com.example.ezcart.data.model.RemoteOrderHead
import com.example.ezcart.data.repository.orders.OrdersRepository
import javax.inject.Inject

class GetOrdersFromDBUseCase @Inject constructor(private val ordersRepository: OrdersRepository) {

    suspend fun execute(): List<RemoteOrderHead> = ordersRepository.getOrderListFromDB()
}