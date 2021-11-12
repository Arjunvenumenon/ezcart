package com.example.ezcart.presentation.registration

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.ezcart.R
import com.example.ezcart.data.db.entities.UserProfile
import com.example.ezcart.databinding.FragmentRegistrationBinding
import com.example.ezcart.presentation.di.Injectable
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RegistrationFragment : Fragment(), Injectable {

    private lateinit var binding: FragmentRegistrationBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: RegistrationViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        binding.buttonRegister.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                validate()
            }

        return binding.root
    }

    private fun validate() {
        binding.progressbar.visibility = View.VISIBLE
        val firstName = binding.editTextFirstName.text.toString()
        val lastName = binding.editTextLastName.text.toString()
        val userId = binding.editTextUserId.text.toString()
        val password = binding.editTextPassword.text.toString()
        val mobileNo = binding.editTextMobile.text.toString()

        val inputs = listOf(firstName, lastName, userId, password, mobileNo)
        val emptyInputs = inputs.any { it.isBlank() }
        if (emptyInputs) {
            binding.progressbar.visibility = View.GONE
            if(firstName.isBlank()) {
                binding.editTextFirstName.error = getString(R.string.this_field_mandatory)
            }
            if(lastName.isBlank()) {
                binding.editTextLastName.error = getString(R.string.this_field_mandatory)
            }
            if(userId.isBlank()) {
                binding.editTextUserId.error = getString(R.string.this_field_mandatory)
            }
            if(password.isBlank()) {
                binding.editTextPassword.error = getString(R.string.this_field_mandatory)
            }
            if(mobileNo.isBlank()) {
                binding.editTextMobile.error = getString(R.string.this_field_mandatory)
            }
            return
        }

        if (!validateUserId(userId) or !validatePhone(mobileNo)) {
            return
        }

        val userProfile = UserProfile(
            firstName = firstName,
            lastName = lastName,
            userId = userId,
            password = password,
            mobileNo = mobileNo
        )
        lifecycleScope.launch {
            viewModel.clearUser()
            viewModel.saveUser(userProfile)
            binding.progressbar.visibility = View.GONE
            backToLoginScreen()
        }

    }

    private fun backToLoginScreen() {
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_registrationFragment_to_loginFragment2)
    }


    private fun validateUserId(userId: String): Boolean {
        return if (!Patterns.EMAIL_ADDRESS.matcher(userId).matches()) {
            binding.editTextUserId.error = getString(R.string.userid_validatuion_error)
            false
        } else {
            true
        }
    }

    private fun validatePhone(phoneNo: String): Boolean {
        return if (!Patterns.PHONE.matcher(phoneNo).matches()) {
            binding.editTextMobile.error = getString(R.string.phone_validatuion_error)
            false
        } else {
            true
        }
    }

}