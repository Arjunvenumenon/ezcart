package com.example.ezcart.data.repository.orders.datasource

import com.example.ezcart.data.db.daos.RemoteOrderHeadDao
import com.example.ezcart.data.model.RemoteOrderHead
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersLocalDataSource @Inject constructor(private val remoteOrderHeadDao: RemoteOrderHeadDao) {

    suspend fun saveOrderToDB(orderList: List<RemoteOrderHead>)
        = remoteOrderHeadDao.insertOrderHead(orderList)

    suspend fun getOrderList(): List<RemoteOrderHead> = withContext(Dispatchers.IO) {
        remoteOrderHeadDao.getOrderItems()
    }
}