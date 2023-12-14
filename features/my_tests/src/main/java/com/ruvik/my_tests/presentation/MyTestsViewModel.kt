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

/**
 * ViewModel for the MyTestsFragment.
 *
 * @param loadUserTestsUseCase The use case for loading user tests.
 * @param myTestsRouter The router for navigation within the MyTests feature.
 */
class MyTestsViewModel(
    private val loadUserTestsUseCase: LoadUserTestsUseCase,
    private val myTestsRouter: MyTestsRouter
) : ViewModel() {

    // LiveData to observe the list of user tests
    private val testsMutableLiveData: MutableLiveData<List<TestBody>> = MutableLiveData()
    val tests: LiveData<List<TestBody>> = testsMutableLiveData

    // Current user's email
    private val login = FirebaseAuth.getInstance().currentUser!!.email!!

    /**
     * Loads user tests and updates the LiveData.
     */
    fun load() {
        CoroutineScope(Dispatchers.IO).launch {
            testsMutableLiveData.postValue(loadUserTestsUseCase.execute(User(login, null)))
        }
    }

    /**
     * Navigates to the screen for finding tests.
     */
    fun goToFindTest() {
        myTestsRouter.goToFindTest()
    }

    /**
     * Navigates to the screen for creating a new test.
     */
    fun goToCreateNewTest() {
        myTestsRouter.goToCreateNewTest()
    }

    /**
     * Navigates to the screen to see the results of a specific test.
     *
     * @param testHashCode The hash code of the test.
     */
    fun goToSeeTestResults(testHashCode: String) {
        myTestsRouter.goToSeeTestResults(testHashCode)
    }
}
