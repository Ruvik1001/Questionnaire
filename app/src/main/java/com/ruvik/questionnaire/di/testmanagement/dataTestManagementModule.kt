package com.ruvik.questionnaire.di.testmanagement

import com.ruvik.data.testmanagement.repository.impl.FirebaseServiceImpl
import com.ruvik.data.testmanagement.repository.impl.TestsRepositoryImpl
import com.ruvik.domain.testmanagement.FirebaseService
import com.ruvik.domain.testmanagement.TestsRepository
import org.koin.dsl.module

val dataTestManagementModule = module {
    single<FirebaseService> {
        FirebaseServiceImpl()
    }

    single<TestsRepository> {
        TestsRepositoryImpl(get())
    }
}