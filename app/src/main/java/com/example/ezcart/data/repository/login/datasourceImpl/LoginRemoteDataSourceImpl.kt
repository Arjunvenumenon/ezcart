package com.example.ezcart.data.repository.login.datasourceImpl

import com.example.ezcart.data.api.RetrofitService
import com.example.ezcart.data.model.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val retrofitService: RetrofitService
) {
    suspend fun getProducts(): Response<LoginResponse>? = retrofitService.getProducts()
}