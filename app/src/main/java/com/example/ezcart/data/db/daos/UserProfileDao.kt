package com.example.ezcart.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ezcart.data.db.entities.UserProfile

@Dao
interface UserProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(userProfile: UserProfile)

    @Query("DELETE FROM user_profile")
    suspend fun deleteUserProfile()
}