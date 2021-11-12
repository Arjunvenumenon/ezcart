package com.example.ezcart.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feedback")
data class Feedback (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val comments: String?,

    val rating: Float?
)