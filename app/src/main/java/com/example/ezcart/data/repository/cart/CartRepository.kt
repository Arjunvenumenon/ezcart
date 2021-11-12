package com.example.ezcart.data.repository.cart

import android.util.Log
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.db.entities.OrderHead
import com.example.ezcart.data.db.entities.OrderItem
import com.example.ezcart.util.getDateForOrder
import javax.inject.Inject

class CartRepository @Inject constructor(private val cartLocalDataSource: CartLocalDataSource) {


    suspend fun getCartItems(): List<Cart> {
        lateinit var cartList: List<Cart>
        try {
            cartList = cartLocalDataSource.getCartItems()
        } catch (exception: Exception) {
            Log.i("Exception",exception.message.toString())
        }

        return cartList
    }

    suspend fun deleteItemFromCart(itemName: String) {
        try {
            cartLocalDataSource.deleteItemFromCart(itemName)
        } catch (exception: Exception) {
            Log.i("Exception",exception.message.toString())
        }
    }

    suspend fun getCartCount(): Int {
        return cartLocalDataSource.getCartCount()
    }

    suspend fun placeOrder(cartList: List<Cart>) {

        val lastOrderNumber = cartLocalDataSource.getMaxOrderNumber() ?: 0
        val currentOrderId = lastOrderNumber + 1
        val totalAmount = cartList.sumOf { it.price ?: 0 }

        val orderList: List<OrderItem> = cartList.map {
            OrderItem(
                order_number = currentOrderId,
                order_item_name = it.item_name,
                order_currency = it.currency,
                order_desc = it.desc,
                order_price = it.price,
                order_expiry_date = it.expiry_date,
                order_quantity = it.quantity
            )}

        val orderHead = OrderHead(
            order_head_number = currentOrderId,
            order_head_date = getDateForOrder(),
            order_head_amount = totalAmount
        )

        cartLocalDataSource.insertOrderItems(orderList)
        cartLocalDataSource.insertOrderHead(orderHead)
        cartLocalDataSource.clearCart()

    }

}