package com.ruvik.create_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ruvik.common.DataTime
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import com.ruvik.domain.testmanagement.usecase.AddNewTestUseCase

/**
 * ViewModel for the Create Test screen.
 *
 * @param addNewTestUseCase The use case for adding a new test.
 * @param createTestRouter The router for navigating to other screens.
 */
class CreateTestViewModel(
    private val addNewTestUseCase: AddNewTestUseCase,
    private val createTestRouter: CreateTestRouter
) : ViewModel() {
    private val test: TestBody = TestBody(
        DataTime.getDataTimeKey(),
        "",
        FirebaseAuth.getInstance().currentUser!!.email!!,
        mutableListOf()
    )

    // LiveData to indicate whether a test needs to be saved
    val needSave = MutableLiveData<Boolean>(false)

    /**
     * Handles recreation events, clearing the test items.
     */
    fun onRecreateCatch() {
        clearItem()
    }

    /**
     * Sets the name of the test.
     *
     * @param name The name of the test.
     */
    fun setTestName(name: String) {
        test.name = name
    }

    /**
     * Retrieves the name of the test.
     *
     * @return The name of the test.
     */
    fun getTestName(): String = test.name

    /**
     * Adds a test item to the test.
     *
     * @param item The test item to be added.
     */
    fun addItem(item: TestItem) {
        test.items = test.items.toMutableList() + item
    }

    /**
     * Clears the test items.
     */
    fun clearItem() {
        test.items = mutableListOf()
    }

    /**
     * Navigates to the My Tests screen after confirming the test creation.
     */
    fun confirm() {
        createTestRouter.goToMyTests()
    }

    /**
     * Adds a new test using the associated use case.
     *
     * @return `true` if the test is successfully added, `false` otherwise.
     */
    suspend fun addNewTest(): Boolean {
        return addNewTestUseCase.execute(test)
    }
}
