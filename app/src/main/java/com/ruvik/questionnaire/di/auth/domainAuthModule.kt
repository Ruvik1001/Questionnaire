package com.ruvik.questionnaire.di.auth

import com.ruvik.data.auth.repository.impl.AuthRepositoryImplData
import com.ruvik.domain.auth.AuthRepository
import com.ruvik.domain.auth.usecase.ResetPasswordUseCase
import com.ruvik.domain.auth.usecase.SignInUseCase
import com.ruvik.domain.auth.usecase.SignUpUseCase
import org.koin.dsl.module

val domainAuthModule = module {
    factory<ResetPasswordUseCase> {
        ResetPasswordUseCase(get<AuthRepository>())
    }

    factory<SignInUseCase> {
        SignInUseCase(get<AuthRepository>())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(get<AuthRepository>())
    }
}