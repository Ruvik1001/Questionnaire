package com.ruvik.my_tests

import com.ruvik.domain.testmanagement.data.testinfo.TestBody

interface MyTestsRouter {
    fun goToCreateNewTest()
    fun goToSeeTestResults(testHashCode: String)
    fun goToFindTest()
}