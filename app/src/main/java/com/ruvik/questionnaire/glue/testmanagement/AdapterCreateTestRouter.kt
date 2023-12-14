package com.ruvik.questionnaire.glue.testmanagement

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.questionnaire.R

/**
 * AdapterCreateTestRouter is an implementation of [CreateTestRouter] using NavController for navigation.
 *
 * @param navController The NavController instance for navigation.
 */
class AdapterCreateTestRouter(
    private var navController: NavController?
) : CreateTestRouter {

    /**
     * Navigate to the MyTestsFragment with pop-up to remove from the back stack.
     */
    override fun goToMyTests() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.myTestsFragment, true)
            .build()
        navController?.navigate(R.id.action_createTestFragment_to_myTestsFragment, null, navOptions)
    }

    /**
     * Switch the NavController instance.
     *
     * @param navControllerNew The new NavController instance.
     */
    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}
