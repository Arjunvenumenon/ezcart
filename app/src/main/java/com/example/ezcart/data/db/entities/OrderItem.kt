package com.example.ezcart.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_item")
data class OrderItem(

    @PrimaryKey
    val order_number: Int,

    val order_item_name: String,

    val order_desc: String?,

    val order_price: Int?,

    val order_currency: String?,

    val order_expiry_date: String?,

    val order_quantity: String?

)