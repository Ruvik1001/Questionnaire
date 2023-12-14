package com.ruvik.sign_up.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.ruvik.common.BaseAlert
import com.ruvik.common.LongToast
import com.ruvik.sign_up.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for sign-up.
 */
class SignUpFragment : Fragment() {

    private val viewModel by viewModel<SignUpViewModel>()
    private lateinit var view: View

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        etEmail = view.findViewById(R.id.editTextEmail)
        etPassword = view.findViewById(R.id.editTextPassword)
        etConfirmPassword = view.findViewById(R.id.editTextConfirmPassword)
        btnSignUp = view.findViewById(R.id.buttonSignUp)
        progressBar = view.findViewById(R.id.progress_circular_sign_up)

        val userData = viewModel.getUserData()
        if (userData.email.isNotBlank()) etEmail.setText(userData.email)
        if (userData.password.isNotBlank()) etPassword.setText(userData.password)

        btnSignUp.setOnClickListener {
            viewModel.setUserData(etEmail.text.toString(), etPassword.text.toString())

            if (viewModel.validateMail())
                etEmail.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.background))
            else
                etEmail.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.light_red))


            if (viewModel.validatePassword())
                etPassword.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.background))
            else
                etPassword.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.light_red))


            if (etPassword.text.toString() == etConfirmPassword.text.toString())
                etConfirmPassword.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.background))
            else
                etConfirmPassword.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.light_red))



            if (
                !viewModel.validateMail() || !viewModel.validatePassword()
                || viewModel.getUserData().password != etConfirmPassword.text.toString()
                ) return@setOnClickListener

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.VISIBLE
                    btnSignUp.isClickable = false
                }

                var isSignInSuccessful: Boolean = false
                try {
                    isSignInSuccessful = viewModel.signUp()
                } finally {
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = ProgressBar.GONE
                        btnSignUp.isClickable = true

                        GlobalScope.launch(Dispatchers.Main) {
                            if (isSignInSuccessful) {
                                BaseAlert.showAlert(
                                    this@SignUpFragment.requireContext(),
                                    getString(R.string.alert_sign_up_title),
                                    getString(R.string.successful_sign_up)
                                )
                            } else {
                                BaseAlert.showAlert(
                                    this@SignUpFragment.requireContext(),
                                    getString(R.string.alert_sign_up_title),
                                    getString(R.string.failure_sigen_up)
                                )
                            }
                        }
                    }
                }
            }
        }

        return view
    }

}