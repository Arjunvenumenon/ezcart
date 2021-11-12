package com.example.ezcart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "product_details")
data class ProductDetails(

    @PrimaryKey
    @SerializedName("item name")
    val item_name: String,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("expiry date")
    val expiry_date: String?,
    @SerializedName("quantity")
    val quantity: String?
): Serializable