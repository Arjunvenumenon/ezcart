package com.example.ezcart.data.repository.cart

import com.example.ezcart.data.db.daos.CartDao
import com.example.ezcart.data.db.daos.OrderHeadDao
import com.example.ezcart.data.db.daos.OrderItemDao
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.db.entities.OrderHead
import com.example.ezcart.data.db.entities.OrderItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartLocalDataSource @Inject constructor(
    private val cartDao: CartDao,
    private val orderItemDao: OrderItemDao,
    private val orderHeadDao: OrderHeadDao
) {

    suspend fun clearCart() {
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.deleteAllFromCart()
        }
    }

    suspend fun deleteItemFromCart(itemName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            cartDao.deleteItemFromCart(itemName)
        }
    }

    suspend fun getCartItems(): List<Cart> = withContext(Dispatchers.IO) {
        cartDao.getCartItems()
    }

    suspend fun getCartCount(): Int = withContext(Dispatchers.IO) {
        cartDao.getCartCount()
    }

    suspend fun getMaxOrderNumber(): Int? = withContext(Dispatchers.IO) {
        orderHeadDao.getMaxOrderNumber()
    }

    suspend fun insertOrderItems(orderItemsList: List<OrderItem>) {
        orderItemDao.insertOrderItems(orderItemsList)
    }

    suspend fun insertOrderHead(orderHead: OrderHead) {
        orderHeadDao.insertOrderHead(orderHead)
    }
}