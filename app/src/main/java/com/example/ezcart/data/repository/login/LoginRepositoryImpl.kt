package com.example.ezcart.data.repository.login

import android.util.Log
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.data.repository.login.datasourceImpl.LoginLocalDataSourceImpl
import com.example.ezcart.data.repository.login.datasourceImpl.LoginRemoteDataSourceImpl
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: LoginRemoteDataSourceImpl,
    private val loginLocalDataSource: LoginLocalDataSourceImpl
) {
    suspend fun getProductsFromApi(): List<ProductDetails> {
       lateinit var productList: List<ProductDetails>
       try {
           val response = loginRemoteDataSource.getProducts()
           val body = response?.body()
           if(body != null) {
               productList = body.data
               loginLocalDataSource.saveProductsToDB(productList)
               Log.i("Data", body.data.toString())
           }

       } catch (exception: Exception){
           Log.i("Exception",exception.message.toString())
       }

       return productList
    }

    suspend fun getProductsFromDB(): List<ProductDetails> {
        lateinit var productList: List<ProductDetails>
        try {
            productList = loginLocalDataSource.getProductsFromDB()
        } catch (exception: Exception) {
            Log.i("Exception",exception.message.toString())
        }

        return productList
    }
}