package com.ruvik.questionnaire.di.testmanagement

import com.ruvik.data.testmanagement.repository.impl.FirebaseServiceImpl
import com.ruvik.data.testmanagement.repository.impl.TestsRepositoryImpl
import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.TestsRepository
import org.koin.dsl.module

/**
 * Koin module for providing implementations related to test management data.
 */
val dataTestManagementModule = module {
    /**
     * Provides a [FirebaseService] using [FirebaseServiceImpl] implementation.
     *
     * @return An instance of [FirebaseService].
     */
    single<FirebaseService> {
        FirebaseServiceImpl()
    }

    /**
     * Provides a [TestsRepository] using [TestsRepositoryImpl] implementation with dependencies.
     *
     * @return An instance of [TestsRepository].
     */
    single<TestsRepository> {
        TestsRepositoryImpl(get())
    }
}
