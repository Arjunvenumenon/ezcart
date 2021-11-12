package com.example.ezcart.data.repository.rate_us

import com.example.ezcart.data.db.entities.Feedback
import javax.inject.Inject

class RateUsRepository @Inject constructor(private val rateUsLocalDataSource: RateUsLocalDataSource) {

    suspend fun saveFeedback(feedback: Feedback) {
        rateUsLocalDataSource.saveFeedback(feedback)
    }
}