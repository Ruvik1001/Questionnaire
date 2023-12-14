package com.ruvik.create_test.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ruvik.common.DataTime
import com.ruvik.create_test.CreateTestRouter
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import com.ruvik.domain.testmanagement.usecase.AddNewTestUseCase

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

    val needSave = MutableLiveData<Boolean>(false)

    fun onRecreateCatch() {
        clearItem()
    }

    fun setTestName(name: String) {
        test.name = name
    }

    fun getTestName(): String = test.name

    fun addItem(item: TestItem) {
        test.items = test.items.toMutableList() + item
    }

    fun clearItem() {
        test.items = mutableListOf()
    }

    fun confirm() {
        createTestRouter.goToMyTests()
    }

    suspend fun addNewTest(): Boolean {
        return addNewTestUseCase.execute(test)
    }

}