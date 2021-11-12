package com.example.ezcart.data.repository.rate_us

import com.example.ezcart.data.db.daos.FeedbackDao
import com.example.ezcart.data.db.entities.Feedback
import javax.inject.Inject

class RateUsLocalDataSource @Inject constructor(private val feedbackDao: FeedbackDao) {

    suspend fun saveFeedback(feedback: Feedback) = feedbackDao.saveFeedback(feedback)
}