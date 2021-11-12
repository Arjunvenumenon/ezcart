package com.example.ezcart.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfile (

    @PrimaryKey
    val firstName: String,

    val lastName: String,

    val userId: String,

    val password: String,

    val mobileNo: String
)