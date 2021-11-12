package com.example.ezcart

import com.example.ezcart.data.db.entities.Cart
import com.example.ezcart.data.model.*

class MockTestUtil {

    companion object {

        fun mockProducts() = ProductDetails("itemName","desc",2,"currency",
            "date","qty")

        fun mockCart() = Cart("itemName","desc", 10, "currency",
            "10-01-2021", "2")

    }
}