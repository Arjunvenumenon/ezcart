package com.example.ezcart.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.ezcart.data.db.AppDatabase
import com.example.ezcart.data.db.daos.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataBaseModule{
    @Singleton
    @Provides
    fun provideProductDetailsDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(app.applicationContext, AppDatabase::class.java,"ezcartclient")
            .build()
    }

    @Singleton
    @Provides
    fun provideProductDetailsDao(appDatabase: AppDatabase): ProuctDetailsDao {
        return appDatabase.productDetailsDao()
    }

    @Singleton
    @Provides
    fun provideCartDao(appDatabase: AppDatabase): CartDao {
        return appDatabase.cartDao()
    }

    @Singleton
    @Provides
    fun provideOrderItemDao(appDatabase: AppDatabase): OrderItemDao {
        return appDatabase.orderItemDao()
    }

    @Singleton
    @Provides
    fun provideOrderHeadDao(appDatabase: AppDatabase): OrderHeadDao {
        return appDatabase.orderHeadDao()
    }

    @Singleton
    @Provides
    fun provideFeedbackDao(appDatabase: AppDatabase): FeedbackDao {
        return appDatabase.feedbackDao()
    }

    @Singleton
    @Provides
    fun provideRemoteOrderHeadDao(appDatabase: AppDatabase): RemoteOrderHeadDao {
        return appDatabase.remoteOrderHeadDao()
    }

    @Singleton
    @Provides
    fun provideUserProfileDao(appDatabase: AppDatabase): UserProfileDao {
        return appDatabase.userProfileDao()
    }

}