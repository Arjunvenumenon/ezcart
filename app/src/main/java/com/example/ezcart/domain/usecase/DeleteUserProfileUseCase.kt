package com.example.ezcart.domain.usecase

import com.example.ezcart.data.repository.registration.RegistrationRepository
import javax.inject.Inject

class DeleteUserProfileUseCase @Inject constructor(
    private val registrationRepository: RegistrationRepository
) {

    suspend fun execute() = registrationRepository.clearUser()
}