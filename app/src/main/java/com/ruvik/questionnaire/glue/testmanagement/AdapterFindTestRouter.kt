package com.ruvik.questionnaire.glue.testmanagement

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.gson.Gson
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.find_test.FindTestRouter
import com.ruvik.questionnaire.R

/**
 * AdapterFindTestRouter is an implementation of [FindTestRouter] using NavController for navigation.
 *
 * @param context The Context instance for resources and operations.
 * @param navController The NavController instance for navigation.
 */
class AdapterFindTestRouter(
    private val context: Context,
    private var navController: NavController?
) : FindTestRouter {

    /**
     * Navigate to the MyTestsFragment with pop-up to remove from the back stack.
     */
    override fun goToMyTests() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.myTestsFragment, true)
            .build()

        navController?.navigate(R.id.action_findTestFragment_to_myTestsFragment, null, navOptions)
    }

    /**
     * Navigate to the RunTestFragment with the provided [TestBody].
     *
     * @param testBody The TestBody object to pass to the destination.
     */
    override fun goToRunTest(testBody: TestBody) {
        val bundle = Bundle().apply {
            putString(
                context.getString(com.ruvik.common.R.string.key_test_body_translation_by_bundle),
                Gson().toJson(testBody)
            )
        }
        navController?.navigate(R.id.action_findTestFragment_to_runTestFragment, bundle)
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
