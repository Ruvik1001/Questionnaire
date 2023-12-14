package com.ruvik.questionnaire.di.auth

import com.ruvik.data.auth.repository.impl.AuthRepositoryImplData
import com.ruvik.domain.auth.AuthRepository
import org.koin.dsl.module

val dataAuthModule = module {
    single<AuthRepository> {
        AuthRepositoryImplData()
    }
}