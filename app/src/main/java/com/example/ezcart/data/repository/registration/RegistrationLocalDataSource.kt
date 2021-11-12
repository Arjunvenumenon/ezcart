package com.example.ezcart.data.repository.registration

import com.example.ezcart.data.db.daos.UserProfileDao
import com.example.ezcart.data.db.entities.UserProfile
import javax.inject.Inject

class RegistrationLocalDataSource @Inject constructor(private val userProfileDao: UserProfileDao) {

    suspend fun saveUser(userProfile: UserProfile) {
        userProfileDao.saveUser(userProfile)
    }

    suspend fun clearUser() {
        userProfileDao.deleteUserProfile()
    }
}