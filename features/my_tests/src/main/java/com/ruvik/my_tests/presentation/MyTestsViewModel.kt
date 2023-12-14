package com.ruvik.my_tests.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ruvik.domain.testmanagement.data.User
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.usecase.LoadUserTestsUseCase
import com.ruvik.my_tests.MyTestsRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyTestsViewModel(
    private val loadUserTestsUseCase: LoadUserTestsUseCase,
    private val myTestsRouter: MyTestsRouter
) : ViewModel() {
    private val testsMutableLiveData: MutableLiveData<List<TestBody>> = MutableLiveData()
    val tests: LiveData<List<TestBody>> = testsMutableLiveData

    val login = FirebaseAuth.getInstance().currentUser!!.email!!

    fun load() {
        CoroutineScope(Dispatchers.IO).launch {
            testsMutableLiveData.postValue(loadUserTestsUseCase.execute(User(login, null)))
        }
    }

    fun goToFindTest() {
        myTestsRouter.goToFindTest()
    }

    fun goToCreateNewTest() {
        myTestsRouter.goToCreateNewTest()
    }

    fun goToSeeTestResults(testHashCode: String) {
        myTestsRouter.goToSeeTestResults(testHashCode)
    }
}