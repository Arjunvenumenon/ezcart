package com.example.ezcart.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezcart.data.db.entities.OrderHead

@Dao
interface OrderHeadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderHead(orderHead: OrderHead)

    @Query("SELECT MAX(order_head_number) FROM order_head")
    suspend fun getMaxOrderNumber(): Int?
}