package com.example.ezcart.data.model

import com.google.gson.annotations.SerializedName

data class RemoteOrderItems(

    @SerializedName("item name")
    val item_name: String,

    @SerializedName("desc")
    val desc: String?,

    @SerializedName("price")
    val price: Int,

    @SerializedName("currency")
    val currency: String?,

    @SerializedName("expiry date")
    val expiry_date: String?,

    @SerializedName("quantity")
    val quantity: String?
)