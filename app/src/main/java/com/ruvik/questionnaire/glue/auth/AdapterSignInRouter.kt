package com.ruvik.questionnaire.glue.auth

import androidx.navigation.NavController
import com.ruvik.questionnaire.R
import com.ruvik.sign_in.SignInRouter

class AdapterSignInRouter(
    private var navController: NavController?
): SignInRouter {
    private val TAG = "AdapterSignInRouter"

    override fun goToMyTests() {
        navController!!.navigate(R.id.action_signInFragment_to_myTestsFragment)
    }

    override fun goToSignUp() {
        navController!!.navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    override fun goToForgotPassword() {
        navController!!.navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
    }

    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}
