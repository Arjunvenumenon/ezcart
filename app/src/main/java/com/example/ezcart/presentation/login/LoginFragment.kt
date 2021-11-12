package com.example.ezcart.presentation.login

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.ezcart.R
import com.example.ezcart.databinding.FragmentLoginBinding
import com.example.ezcart.presentation.di.Injectable
import com.example.ezcart.util.isNetworkAvailable
import com.example.ezcart.util.showToast
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern
import javax.inject.Inject


class LoginFragment: Fragment(), Injectable {

    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                "(?=.*[A-Z])" +
                "(?=.*[0-9])" +
                ".{6,}" +
                "$")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, null, false)

        setHasOptionsMenu(true)

        binding.buttonLogin.clicks()
            .throttleFirst(100, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               validate()
            }, {errorHandle()})


        binding.textViewSignUp.setOnClickListener{
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        return binding.root
    }

    private fun getProducts(){
        if(isNetworkAvailable(requireContext())) {
            binding.loginProgressbar.visibility = View.VISIBLE
                viewModel.getProducts().observe(viewLifecycleOwner) {
                    if(it != null) {
                        binding.loginProgressbar.visibility = View.GONE
                        NavHostFragment.findNavController(this)
                            .navigate(R.id.action_loginFragment_to_home_graph)
                    } else {
                        binding.loginProgressbar.visibility = View.GONE
                    }
                }
        } else {
            showToast(requireContext(),"No Connection")
        }
    }

    private fun validate() {
        if (!validateUserId() or !validatePassword()) {
            return
        } else {
            getProducts()
        }

    }

    private fun validatePassword(): Boolean {
        val passwordInput: String = binding.loginEditTextPassword.text.toString().trim()

        return if (passwordInput.isEmpty()) {
            binding.loginEditTextPassword.error = getString(R.string.password_field_empty_warning)
            false
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            binding.loginEditTextPassword.error = getString(R.string.password_validation_error)
            false
        } else {
            binding.loginEditTextPassword.error = null
            true
        }
    }

    private fun validateUserId(): Boolean {
        val userId = binding.loginEditTextUserId.text.toString()

        return if(userId.isEmpty()) {
            binding.loginEditTextUserId.error = getString(R.string.userid_field_empty_warning)
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userId).matches()) {
            binding.loginEditTextUserId.error = getString(R.string.userid_validatuion_error)
            false
        } else {
            true
        }
    }

    private fun errorHandle() {
        Log.e("Error", "login button click error")
    }

}