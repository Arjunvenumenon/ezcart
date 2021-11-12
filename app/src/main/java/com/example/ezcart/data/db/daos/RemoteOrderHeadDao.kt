package com.example.ezcart.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezcart.data.model.RemoteOrderHead

@Dao
interface RemoteOrderHeadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderHead(remoteOrderHead: List<RemoteOrderHead>)

    @Query("SELECT * FROM remote_order_head")
    suspend fun getOrderItems(): List<RemoteOrderHead>
}