package com.ruvik.questionnaire.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.find_test.FindTestRouter
import com.ruvik.my_tests.MyTestsRouter
import com.ruvik.questionnaire.R
import com.ruvik.questionnaire.glue.auth.AdapterSignInRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterCreateTestRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterFindTestRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterMyTestsRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterRunTestRouter
import com.ruvik.run_test.RunTestRouter
import com.ruvik.sign_in.SignInRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class HostActivity : AppCompatActivity() {

    private val adapterSignInRouter: SignInRouter by inject()
    private val adapterMyTestsRouter: MyTestsRouter by inject()
    private val adapterCreateTestRouter: CreateTestRouter by inject()
    private val adapterFindTestRouter: FindTestRouter by inject()
    private val adapterRunTestRouter: RunTestRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        CoroutineScope(Dispatchers.Main).launch {
            init()
        }
    }

    private suspend fun init() {
        val navController = findNavController(R.id.fragmentContainerView)
        (adapterSignInRouter as AdapterSignInRouter).switchNavController(navController)
        (adapterMyTestsRouter as AdapterMyTestsRouter).switchNavController(navController)
        (adapterCreateTestRouter as AdapterCreateTestRouter).switchNavController(navController)
        (adapterFindTestRouter as AdapterFindTestRouter).switchNavController(navController)
        (adapterRunTestRouter as AdapterRunTestRouter).switchNavController(navController)
    }

    override fun onBackPressed() {
        if (!findNavController(R.id.fragmentContainerView).popBackStack()) {
            super.onBackPressed()
        }
    }

}
