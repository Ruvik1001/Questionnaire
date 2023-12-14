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

val featuresModule = module {
    viewModel<SignInViewModel> {
        SignInViewModel(
            signInUseCase = get(),
            signInRouter = get()
        )
    }


    viewModel<SignUpViewModel> {
        SignUpViewModel(
            context = androidContext(),
            signUpUseCase = get()
        )
    }


    viewModel<ForgotPasswordViewModel> {
        ForgotPasswordViewModel(
            context = androidContext(),
            resetPasswordUseCase = get()
        )
    }


    viewModel<MyTestsViewModel> {
        MyTestsViewModel(
            loadUserTestsUseCase = get(),
            myTestsRouter = get()
        )
    }


    viewModel<CreateTestViewModel> {
        CreateTestViewModel(
            addNewTestUseCase = get(),
            createTestRouter = get()
        )
    }

    viewModel<FindTestViewModel> {
        FindTestViewModel(
            findTestByHashCodeUseCase = get(),
            findTestRouter = get()
        )
    }

    viewModel<RunTestViewModel> {
        RunTestViewModel(
            addAnswerUseCase = get(),
            runTestRouter = get()
        )
    }

    viewModel<SeeResultsViewModel> {
        SeeResultsViewModel(
            loadTestResultsUseCase = get()
        )
    }
}