package com.example.ezcart.presentation.rate_us

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ezcart.data.db.entities.Feedback
import com.example.ezcart.databinding.FragmentRateUsBinding
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.util.showToast
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class RateUsFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentRateUsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: RateUsViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentRateUsBinding.inflate(inflater, container, false)

       binding.submitFeedback.setOnClickListener {
           validate()
       }

       return binding.root
    }

    private fun validate() {
        val rating = binding.ratingBar.rating
        val comments = binding.feedbackComments.text.toString()

        if(comments.isEmpty()) {
            showToast(requireContext(),"Comment should not be null")
        } else if(comments.length > 500) {
            showToast(requireContext(),"Comment should not exceed 500 chars")
        } else {
            saveFeedback(rating, comments)
        }
    }

    private fun saveFeedback(rating: Float, comments: String) {
        val feedback = Feedback(
            id = 0,
            comments = comments,
            rating = rating
        )
        lifecycleScope.launch {
            viewModel.saveFeedback(feedback)
            showToast(requireContext(),"Feedback submitted successfully")
            clearFields()
            backToHomeScreen()
        }
    }

    private fun clearFields() {
        binding.ratingBar.rating = 0F
        binding.feedbackComments.setText("")
    }

    private fun backToHomeScreen() {
        requireActivity().onBackPressed()
    }

}