package com.example.ezcart.presentation.registration

import androidx.lifecycle.ViewModel
import com.example.ezcart.data.db.entities.UserProfile
import com.example.ezcart.domain.usecase.DeleteUserProfileUseCase
import com.example.ezcart.domain.usecase.SaveUserProfileUseCase
import com.example.ezcart.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class RegistrationViewModel @Inject constructor(
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val deleteUserProfileUseCase: DeleteUserProfileUseCase
): ViewModel() {

    suspend fun saveUser(userProfile: UserProfile) {
        saveUserProfileUseCase.execute(userProfile)
    }

    suspend fun clearUser() {
        deleteUserProfileUseCase.execute()
    }
}