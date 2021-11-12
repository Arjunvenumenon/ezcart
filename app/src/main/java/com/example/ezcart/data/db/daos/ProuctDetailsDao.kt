package com.example.ezcart.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezcart.data.model.ProductDetails

@Dao
interface ProuctDetailsDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun saveProducts(productDetails : List<ProductDetails>)

@Query("DELETE FROM product_details")
suspend fun deleteAllProducts()

@Query("SELECT * FROM product_details")
suspend fun getProducts():List<ProductDetails>
}