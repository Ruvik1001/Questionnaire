package com.ruvik.questionnaire.di.auth

import com.ruvik.data.auth.repository.impl.AuthRepositoryImplData
import com.ruvik.domain.auth.AuthRepository
import org.koin.dsl.module

/**
 * Koin module for data layer dependencies related to authentication.
 */
val dataAuthModule = module {
    /**
     * Provides an implementation of [AuthRepository] using [AuthRepositoryImplData].
     *
     * @return An instance of [AuthRepositoryImplData].
     */
    single<AuthRepository> {
        AuthRepositoryImplData()
    }
}
