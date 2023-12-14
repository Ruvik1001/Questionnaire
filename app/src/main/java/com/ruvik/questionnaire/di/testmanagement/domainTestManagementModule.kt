package com.ruvik.questionnaire.di.testmanagement

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.usecase.AddAnswerUseCase
import com.ruvik.domain.testmanagement.usecase.AddNewTestUseCase
import com.ruvik.domain.testmanagement.usecase.FindTestByHashCodeUseCase
import com.ruvik.domain.testmanagement.usecase.LoadTestResultsUseCase
import com.ruvik.domain.testmanagement.usecase.LoadUserTestsUseCase
import org.koin.dsl.module

val domainTestManagementModule = module {
    factory<AddAnswerUseCase> {
        AddAnswerUseCase(get<TestsRepository>())
    }

    factory<AddNewTestUseCase> {
        AddNewTestUseCase(get<TestsRepository>())
    }

    factory<LoadTestResultsUseCase> {
        LoadTestResultsUseCase(get<TestsRepository>())
    }

    factory<LoadUserTestsUseCase> {
        LoadUserTestsUseCase(get<TestsRepository>())
    }

    factory<FindTestByHashCodeUseCase> {
        FindTestByHashCodeUseCase(get<TestsRepository>())
    }

}