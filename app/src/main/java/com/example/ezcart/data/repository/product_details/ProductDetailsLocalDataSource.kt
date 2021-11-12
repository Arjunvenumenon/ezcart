package com.example.ezcart.data.repository.product_details

import com.example.ezcart.data.db.daos.CartDao
import com.example.ezcart.data.db.entities.Cart
import javax.inject.Inject

class ProductDetailsLocalDataSource @Inject constructor(val cartDao: CartDao) {

    suspend fun addToCart(cart: Cart) = cartDao.addToCart(cart)

}