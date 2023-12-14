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
import com.ruvik.questionnaire.presentation.HostActivity
import com.ruvik.run_test.RunTestRouter
import com.ruvik.sign_in.SignInRouter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SignInRouter> {
        AdapterSignInRouter(null)
    }

    single<MyTestsRouter> {
        AdapterMyTestsRouter(context = androidContext(), null)
    }

    single<CreateTestRouter> {
        AdapterCreateTestRouter(null)
    }

    single<FindTestRouter> {
        AdapterFindTestRouter(context = androidContext(), null)
    }

    single<RunTestRouter> {
        AdapterRunTestRouter(null)
    }
}