package com.ruvik.questionnaire.di.app

import android.content.Context
import androidx.navigation.NavController
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.find_test.FindTestRouter
import com.ruvik.my_tests.MyTestsRouter
import com.ruvik.questionnaire.glue.auth.AdapterSignInRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterCreateTestRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterFindTestRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterMyTestsRouter
import com.ruvik.questionnaire.glue.testmanagement.AdapterRunTestRouter
import com.ruvik.run_test.RunTestRouter
import com.ruvik.sign_in.SignInRouter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module for app-level dependencies.
 */
val appModule = module {
    /**
     * Provides an implementation of [SignInRouter] using [AdapterSignInRouter].
     *
     * @param navController The [NavController] used for navigation.
     * @return An instance of [AdapterSignInRouter].
     */
    single<SignInRouter> { AdapterSignInRouter(navController = null) }

    /**
     * Provides an implementation of [MyTestsRouter] using [AdapterMyTestsRouter].
     *
     * @param context The application [Context].
     * @param navController The [NavController] used for navigation.
     * @return An instance of [AdapterMyTestsRouter].
     */
    single<MyTestsRouter> {
        AdapterMyTestsRouter(
            context = androidContext(),
            navController = null
        )
    }

    /**
     * Provides an implementation of [CreateTestRouter] using [AdapterCreateTestRouter].
     *
     * @param navController The [NavController] used for navigation.
     * @return An instance of [AdapterCreateTestRouter].
     */
    single<CreateTestRouter> { AdapterCreateTestRouter(navController = null) }

    /**
     * Provides an implementation of [FindTestRouter] using [AdapterFindTestRouter].
     *
     * @param context The application [Context].
     * @param navController The [NavController] used for navigation.
     * @return An instance of [AdapterFindTestRouter].
     */
    single<FindTestRouter> {
        AdapterFindTestRouter(
            context = androidContext(),
            navController = null
        )
    }

    /**
     * Provides an implementation of [RunTestRouter] using [AdapterRunTestRouter].
     *
     * @param navController The [NavController] used for navigation.
     * @return An instance of [AdapterRunTestRouter].
     */
    single<RunTestRouter> { AdapterRunTestRouter(navController = null) }
}
