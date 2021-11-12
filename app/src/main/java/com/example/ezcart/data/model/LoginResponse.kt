package com.example.ezcart.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("data")
    val `data`: List<ProductDetails>
)