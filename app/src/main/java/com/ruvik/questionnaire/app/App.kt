package com.ruvik.questionnaire.app

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.ruvik.questionnaire.di.app.appModule
import com.ruvik.questionnaire.di.auth.dataAuthModule
import com.ruvik.questionnaire.di.auth.domainAuthModule
import com.ruvik.questionnaire.di.features.featuresModule
import com.ruvik.questionnaire.di.testmanagement.dataTestManagementModule
import com.ruvik.questionnaire.di.testmanagement.domainTestManagementModule
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

/**
 * Custom Application class for initializing Koin dependency injection.
 */
class App : Application() {
    /**
     * Called when the application is starting. Responsible for initializing Koin modules.
     */

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
        FirebaseApp.getInstance().apply {
            if (options.apiKey.isNullOrEmpty()) {
                throw RuntimeException("Firebase API key is missing. Check your google-services.json file.")
            }
            Log.d("FirebaseApp", "Initialized with API key: ${options.apiKey}")
        }


        startKoin {
            androidContext(this@App)
            modules(
                appModule,

                dataAuthModule,
                domainAuthModule,

                dataTestManagementModule,
                domainTestManagementModule,

                featuresModule
            )
        }
    }
}