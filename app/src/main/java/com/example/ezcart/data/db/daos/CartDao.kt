package com.example.ezcart.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezcart.data.db.entities.Cart

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: Cart)

    @Query("DELETE FROM cart")
    suspend fun deleteAllFromCart()

    @Query("SELECT * FROM cart")
    suspend fun getCartItems():List<Cart>

    @Query("DELETE FROM cart WHERE item_name=:itemName")
    suspend fun deleteItemFromCart(itemName: String)

    @Query("SELECT COUNT(*) FROM cart")
    suspend fun getCartCount(): Int
}