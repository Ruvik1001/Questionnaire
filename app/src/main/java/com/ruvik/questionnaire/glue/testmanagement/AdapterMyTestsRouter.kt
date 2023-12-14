package com.ruvik.questionnaire.glue.testmanagement

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import com.ruvik.my_tests.MyTestsRouter
import com.ruvik.questionnaire.R

class AdapterMyTestsRouter(
    private val context: Context,
    private var navController: NavController?
): MyTestsRouter {
    private val TAG = "AdapterMyTestsRouter"

    override fun goToCreateNewTest() {
        navController!!.navigate(R.id.action_myTestsFragment_to_createTestFragment)
    }

    override fun goToSeeTestResults(testHashCode: String) {
        val bundle = Bundle()
        bundle.putString(context.getString(com.ruvik.common.R.string.key_test_hash_translation_by_bundle), testHashCode)
        navController!!.navigate(R.id.action_myTestsFragment_to_seeResultsFragment, bundle)
    }

    override fun goToFindTest() {
        navController!!.navigate(R.id.action_myTestsFragment_to_findTestFragment)
        navController!!.popBackStack(R.id.findTestFragment, false)
    }

    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}