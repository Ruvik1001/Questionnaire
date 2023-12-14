package com.ruvik.questionnaire.glue.testmanagement

import androidx.navigation.NavController
import com.ruvik.run_test.RunTestRouter

/**
 * AdapterRunTestRouter is an implementation of [RunTestRouter] using NavController for navigation.
 *
 * @param navController The NavController instance for navigation.
 */
class AdapterRunTestRouter(private var navController: NavController?) : RunTestRouter {

    /**
     * Navigate back by popping the back stack.
     */
    override fun goBack() {
        navController?.popBackStack()
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
