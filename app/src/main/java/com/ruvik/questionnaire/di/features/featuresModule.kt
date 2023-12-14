package com.ruvik.questionnaire.di.features

import com.ruvik.create_test.presentation.CreateTestViewModel
import com.ruvik.find_test.presentation.FindTestViewModel
import com.ruvik.forgot_password.presentation.ForgotPasswordViewModel
import com.ruvik.my_tests.presentation.MyTestsViewModel
import com.ruvik.run_test.presentation.RunTestViewModel
import com.ruvik.see_results.presentation.SeeResultsViewModel
import com.ruvik.sign_in.presentation.SignInViewModel
import com.ruvik.sign_up.presentation.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for providing ViewModel instances for various features.
 */
val featuresModule = module {
    /**
     * Provides a [SignInViewModel] using [SignInViewModel] implementation with dependencies.
     *
     * @return An instance of [SignInViewModel].
     */
    viewModel<SignInViewModel> {
        SignInViewModel(
            signInUseCase = get(),
            signInRouter = get()
        )
    }

    /**
     * Provides a [SignUpViewModel] using [SignUpViewModel] implementation with dependencies.
     *
     * @return An instance of [SignUpViewModel].
     */
    viewModel<SignUpViewModel> {
        SignUpViewModel(
            context = androidContext(),
            signUpUseCase = get()
        )
    }

    /**
     * Provides a [ForgotPasswordViewModel] using [ForgotPasswordViewModel] implementation with dependencies.
     *
     * @return An instance of [ForgotPasswordViewModel].
     */
    viewModel<ForgotPasswordViewModel> {
        ForgotPasswordViewModel(
            context = androidContext(),
            resetPasswordUseCase = get()
        )
    }

    /**
     * Provides a [MyTestsViewModel] using [MyTestsViewModel] implementation with dependencies.
     *
     * @return An instance of [MyTestsViewModel].
     */
    viewModel<MyTestsViewModel> {
        MyTestsViewModel(
            loadUserTestsUseCase = get(),
            myTestsRouter = get()
        )
    }

    /**
     * Provides a [CreateTestViewModel] using [CreateTestViewModel] implementation with dependencies.
     *
     * @return An instance of [CreateTestViewModel].
     */
    viewModel<CreateTestViewModel> {
        CreateTestViewModel(
            addNewTestUseCase = get(),
            createTestRouter = get()
        )
    }

    /**
     * Provides a [FindTestViewModel] using [FindTestViewModel] implementation with dependencies.
     *
     * @return An instance of [FindTestViewModel].
     */
    viewModel<FindTestViewModel> {
        FindTestViewModel(
            findTestByHashCodeUseCase = get(),
            findTestRouter = get()
        )
    }

    /**
     * Provides a [RunTestViewModel] using [RunTestViewModel] implementation with dependencies.
     *
     * @return An instance of [RunTestViewModel].
     */
    viewModel<RunTestViewModel> {
        RunTestViewModel(
            addAnswerUseCase = get(),
            runTestRouter = get()
        )
    }

    /**
     * Provides a [SeeResultsViewModel] using [SeeResultsViewModel] implementation with dependencies.
     *
     * @return An instance of [SeeResultsViewModel].
     */
    viewModel<SeeResultsViewModel> {
        SeeResultsViewModel(
            loadTestResultsUseCase = get()
        )
    }
}
