package com.ruvik.questionnaire.di.auth

import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.usecase.ResetPasswordUseCase
import com.ruvik.domain.auth.usecase.SignInUseCase
import com.ruvik.domain.auth.usecase.SignUpUseCase
import org.koin.dsl.module

/**
 * Koin module for domain layer dependencies related to authentication.
 */
val domainAuthModule = module {
    /**
     * Provides a [ResetPasswordUseCase] using [ResetPasswordUseCase] implementation with [AuthRepository].
     *
     * @return An instance of [ResetPasswordUseCase].
     */
    factory<ResetPasswordUseCase> {
        ResetPasswordUseCase(get<AuthRepository>())
    }

    /**
     * Provides a [SignInUseCase] using [SignInUseCase] implementation with [AuthRepository].
     *
     * @return An instance of [SignInUseCase].
     */
    factory<SignInUseCase> {
        SignInUseCase(get<AuthRepository>())
    }

    /**
     * Provides a [SignUpUseCase] using [SignUpUseCase] implementation with [AuthRepository].
     *
     * @return An instance of [SignUpUseCase].
     */
    factory<SignUpUseCase> {
        SignUpUseCase(get<AuthRepository>())
    }
}
