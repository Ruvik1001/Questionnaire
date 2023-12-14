package com.ruvik.questionnaire.di.testmanagement

import com.ruvik.domain.testmanagement.TestsRepository
import com.ruvik.domain.testmanagement.usecase.AddAnswerUseCase
import com.ruvik.domain.testmanagement.usecase.AddNewTestUseCase
import com.ruvik.domain.testmanagement.usecase.FindTestByHashCodeUseCase
import com.ruvik.domain.testmanagement.usecase.LoadTestResultsUseCase
import com.ruvik.domain.testmanagement.usecase.LoadUserTestsUseCase
import org.koin.dsl.module

/**
 * Koin module for providing use cases related to test management domain.
 */
val domainTestManagementModule = module {
    /**
     * Provides an [AddAnswerUseCase] using [AddAnswerUseCase] implementation with dependencies.
     *
     * @return An instance of [AddAnswerUseCase].
     */
    factory<AddAnswerUseCase> {
        AddAnswerUseCase(get<TestsRepository>())
    }

    /**
     * Provides an [AddNewTestUseCase] using [AddNewTestUseCase] implementation with dependencies.
     *
     * @return An instance of [AddNewTestUseCase].
     */
    factory<AddNewTestUseCase> {
        AddNewTestUseCase(get<TestsRepository>())
    }

    /**
     * Provides a [LoadTestResultsUseCase] using [LoadTestResultsUseCase] implementation with dependencies.
     *
     * @return An instance of [LoadTestResultsUseCase].
     */
    factory<LoadTestResultsUseCase> {
        LoadTestResultsUseCase(get<TestsRepository>())
    }

    /**
     * Provides a [LoadUserTestsUseCase] using [LoadUserTestsUseCase] implementation with dependencies.
     *
     * @return An instance of [LoadUserTestsUseCase].
     */
    factory<LoadUserTestsUseCase> {
        LoadUserTestsUseCase(get<TestsRepository>())
    }

    /**
     * Provides a [FindTestByHashCodeUseCase] using [FindTestByHashCodeUseCase] implementation with dependencies.
     *
     * @return An instance of [FindTestByHashCodeUseCase].
     */
    factory<FindTestByHashCodeUseCase> {
        FindTestByHashCodeUseCase(get<TestsRepository>())
    }
}
