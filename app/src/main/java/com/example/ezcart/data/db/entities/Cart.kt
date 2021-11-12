package com.example.ezcart.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart (
    @PrimaryKey
    val item_name: String,

    val desc: String?,

    val price: Int?,

    val currency: String?,

    val expiry_date: String?,

    val quantity: String?
)