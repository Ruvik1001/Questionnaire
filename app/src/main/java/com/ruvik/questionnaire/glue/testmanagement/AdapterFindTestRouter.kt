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

class AdapterFindTestRouter(
    private val context: Context,
    private var navController: NavController?
): FindTestRouter {
    private val TAG = "AdapterFindTestRouter"

    override fun goToMyTests() {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.myTestsFragment, true)
            .build()

        navController!!.navigate(R.id.action_findTestFragment_to_myTestsFragment, null, navOptions)
    }


    override fun goToRunTest(testBody: TestBody) {
        val bundle = Bundle()
        bundle.putString(context.getString(com.ruvik.common.R.string.key_test_body_translation_by_bundle), Gson().toJson(testBody))
        navController!!.navigate(R.id.action_findTestFragment_to_runTestFragment, bundle)
    }

    fun switchNavController(navControllerNew: NavController) {
        navController = navControllerNew
    }
}