package com.example.ezcart.data.repository.login.datasourceImpl

import com.example.ezcart.data.db.daos.ProuctDetailsDao
import com.example.ezcart.data.model.ProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginLocalDataSourceImpl @Inject constructor(private val productDetailsDao: ProuctDetailsDao){
    suspend fun saveProductsToDB(products: List<ProductDetails>) {
        CoroutineScope(Dispatchers.IO).launch {
            productDetailsDao.saveProducts(products)
        }
    }

    suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            productDetailsDao.deleteAllProducts()
        }
    }

    suspend fun getProductsFromDB(): List<ProductDetails> = withContext(Dispatchers.IO) {
       productDetailsDao.getProducts()
    }
}