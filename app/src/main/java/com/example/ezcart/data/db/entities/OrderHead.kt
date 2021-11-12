package com.example.ezcart.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_head")
data class OrderHead (

    @PrimaryKey
    val order_head_number: Int,

    val order_head_date: String,

    val order_head_amount: Int

)