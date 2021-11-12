package com.example.ezcart.presentation.rate_us

import androidx.lifecycle.ViewModel
import com.example.ezcart.data.db.entities.Feedback
import com.example.ezcart.data.repository.rate_us.RateUsRepository
import javax.inject.Inject

class RateUsViewModel @Inject constructor(
    private val rateUsRepository: RateUsRepository
): ViewModel() {

    suspend fun saveFeedback(feedback: Feedback) {
        rateUsRepository.saveFeedback(feedback)
    }

}