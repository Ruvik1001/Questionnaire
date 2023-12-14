package com.ruvik.questionnaire.glue.testmanagement

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.questionnaire.R

class AdapterCreateTestRouter(
    private var navController: NavController?
): CreateTestRouter {
    private val TAG = "AdapterCreateTestRouter"

    override fun goToMyTests() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.myTestsFragment, true)
            .build()
        navController!!.navigate(R.id.action_createTestFragment_to_myTestsFragment, null, navOptions)
    }

    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}