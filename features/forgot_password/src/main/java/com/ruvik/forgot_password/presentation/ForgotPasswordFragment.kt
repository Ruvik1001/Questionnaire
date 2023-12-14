package com.ruvik.forgot_password.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.ruvik.common.BaseAlert
import com.ruvik.forgot_password.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for reset password.
 */
class ForgotPasswordFragment : Fragment() {

    private val viewModel by viewModel<ForgotPasswordViewModel>()
    private lateinit var view: View

    private lateinit var etEmail: EditText
    private lateinit var btnResetPassword: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        etEmail = view.findViewById(R.id.editTextResetEmail)
        btnResetPassword = view.findViewById(R.id.buttonResetPassword)
        progressBar = view.findViewById(R.id.progress_circular)

        val email: String = viewModel.getEmail()
        if (email.isNotBlank()) etEmail.setText(email)

        btnResetPassword.setOnClickListener {
            viewModel.setEmail(etEmail.text.toString())

            if (viewModel.validateMail())
                etEmail.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.background))
            else
                etEmail.setBackgroundColor(resources.getColor(com.ruvik.common.R.color.light_red))

            if (!viewModel.validateMail())
                return@setOnClickListener

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.VISIBLE
                    btnResetPassword.isClickable = false
                }

                var isSignInSuccessful: Boolean = false
                try {
                    isSignInSuccessful = viewModel.resetPassword()
                } finally {
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = ProgressBar.GONE
                        btnResetPassword.isClickable = true

                        GlobalScope.launch(Dispatchers.Main) {
                            if (isSignInSuccessful) {
                                BaseAlert.showAlert(
                                    this@ForgotPasswordFragment.requireContext(),
                                    getString(R.string.title_reset_password),
                                    getString(R.string.succesful_reset_password)
                                )
                            } else {
                                BaseAlert.showAlert(
                                    this@ForgotPasswordFragment.requireContext(),
                                    getString(R.string.title_reset_password),
                                    getString(R.string.failure_reset_password)
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