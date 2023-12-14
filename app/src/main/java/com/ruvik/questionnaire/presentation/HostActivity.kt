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

/**
 * HostActivity serves as the main activity of the application responsible for navigation setup.
 */
class HostActivity : AppCompatActivity() {

    // Injected dependencies for navigation routers
    private val adapterSignInRouter: SignInRouter by inject()
    private val adapterMyTestsRouter: MyTestsRouter by inject()
    private val adapterCreateTestRouter: CreateTestRouter by inject()
    private val adapterFindTestRouter: FindTestRouter by inject()
    private val adapterRunTestRouter: RunTestRouter by inject()

    /**
     * Called when the activity is first created. Sets up the content view and initializes the navigation.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        CoroutineScope(Dispatchers.Main).launch {
            init()
        }
    }

    /**
     * Initialize the navigation setup.
     */
    private suspend fun init() {
        // Find the NavController associated with the NavHostFragment
        val navController = findNavController(R.id.fragmentContainerView)

        // Switch NavController instances for each router
        (adapterSignInRouter as AdapterSignInRouter).switchNavController(navController)
        (adapterMyTestsRouter as AdapterMyTestsRouter).switchNavController(navController)
        (adapterCreateTestRouter as AdapterCreateTestRouter).switchNavController(navController)
        (adapterFindTestRouter as AdapterFindTestRouter).switchNavController(navController)
        (adapterRunTestRouter as AdapterRunTestRouter).switchNavController(navController)
    }

    /**
     * Handle the back button press. Pop the back stack if available; otherwise, perform the default behavior.
     */
    override fun onBackPressed() {
        if (!findNavController(R.id.fragmentContainerView).popBackStack()) {
            super.onBackPressed()
        }
    }
}

