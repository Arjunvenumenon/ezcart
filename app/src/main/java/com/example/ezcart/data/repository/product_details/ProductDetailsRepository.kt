package com.example.ezcart.data.repository.product_details

import android.util.Log
import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.model.ProductDetails
import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(
    private val productDetailsLocalDataSource: ProductDetailsLocalDataSource
) {

    suspend fun addToCart(productDetails: ProductDetails) {
        try {
            val cart = Cart(
                item_name = productDetails.item_name,
                desc = productDetails.desc,
                price = productDetails.price,
                currency = productDetails.currency,
                expiry_date = productDetails.expiry_date,
                quantity = productDetails.quantity
            )
            productDetailsLocalDataSource.addToCart(cart)
        } catch (exception: Exception) {
            Log.i("Exception",exception.message.toString())
        }
    }

}