package com.example.ezcart.domain.usecase

import com.example.ezcart.data.db.entities.UserProfile
import com.example.ezcart.data.repository.registration.RegistrationRepository
import javax.inject.Inject

class SaveUserProfileUseCase @Inject constructor(
    private val registrationRepository: RegistrationRepository
) {
    suspend fun execute(userProfile: UserProfile) = registrationRepository.saveUser(userProfile)
}