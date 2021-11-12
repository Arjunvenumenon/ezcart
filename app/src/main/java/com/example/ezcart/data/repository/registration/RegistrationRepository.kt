package com.example.ezcart.data.repository.registration

import com.example.ezcart.data.db.entities.UserProfile
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
    private val registrationLocalDataSource: RegistrationLocalDataSource
) {

    suspend fun saveUser(userProfile: UserProfile) {
        registrationLocalDataSource.saveUser(userProfile)
    }

    suspend fun clearUser() {
        registrationLocalDataSource.clearUser()
    }
}