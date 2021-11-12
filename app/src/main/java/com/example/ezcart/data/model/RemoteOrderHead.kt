package com.example.ezcart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "remote_order_head")
data class RemoteOrderHead(

    @PrimaryKey
    @SerializedName("order id")
    val order_id: Int,

    @SerializedName("userr id")
    val user_id : Int,

    @SerializedName("order date")
    val order_date: String,

    @SerializedName("order total")
    val order_total: Int,

    @SerializedName("data")
    val data: List<RemoteOrderItems>,
)