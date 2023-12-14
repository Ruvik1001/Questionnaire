package com.ruvik.questionnaire.glue.testmanagement

import androidx.navigation.NavController
import com.ruvik.run_test.RunTestRouter

class AdapterRunTestRouter(private var navController: NavController?): RunTestRouter {
    override fun goBack() {
        navController!!.popBackStack()
    }

    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}