package com.example.ezcart.data.api

import com.example.ezcart.data.model.LoginResponse
import com.example.ezcart.data.model.OrderResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.POST

interface RetrofitService {

    @POST("login")
    suspend fun getProducts(
    ): Response<LoginResponse>?

    @POST("getorders")
    fun getOrdersFromServer(
    ): Observable<OrderResponse>
}