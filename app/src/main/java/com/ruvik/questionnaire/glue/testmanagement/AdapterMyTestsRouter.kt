package com.ruvik.questionnaire.glue.testmanagement

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.ruvik.my_tests.MyTestsRouter
import com.ruvik.questionnaire.R

/**
 * AdapterMyTestsRouter is an implementation of [MyTestsRouter] using NavController for navigation.
 *
 * @param context The Context instance for resources and operations.
 * @param navController The NavController instance for navigation.
 */
class AdapterMyTestsRouter(
    private val context: Context,
    private var navController: NavController?
) : MyTestsRouter {

    /**
     * Navigate to the CreateTestFragment.
     */
    override fun goToCreateNewTest() {
        navController?.navigate(R.id.action_myTestsFragment_to_createTestFragment)
    }

    /**
     * Navigate to the SeeResultsFragment with the provided test hash code.
     *
     * @param testHashCode The hash code of the test to show results for.
     */
    override fun goToSeeTestResults(testHashCode: String) {
        val bundle = Bundle().apply {
            putString(
                context.getString(com.ruvik.common.R.string.key_test_hash_translation_by_bundle),
                testHashCode
            )
        }
        navController?.navigate(R.id.action_myTestsFragment_to_seeResultsFragment, bundle)
    }

    /**
     * Navigate to the FindTestFragment and pop the back stack to remove it.
     */
    override fun goToFindTest() {
        navController?.navigate(R.id.action_myTestsFragment_to_findTestFragment)
        navController?.popBackStack(R.id.findTestFragment, false)
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
