package com.example.ezcart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ezcart.data.db.daos.*
import com.example.ezcart.data.db.entities.*
import com.example.ezcart.data.model.ProductDetails
import com.example.ezcart.data.model.RemoteOrderHead

@Database(entities = [
    ProductDetails::class,
    Cart::class,
    OrderItem::class,
    OrderHead::class,
    Feedback::class,
    RemoteOrderHead::class,
    UserProfile::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDetailsDao(): ProuctDetailsDao
    abstract fun cartDao(): CartDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun orderHeadDao(): OrderHeadDao
    abstract fun feedbackDao(): FeedbackDao
    abstract fun remoteOrderHeadDao(): RemoteOrderHeadDao
    abstract fun userProfileDao(): UserProfileDao
}