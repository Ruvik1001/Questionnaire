package com.ruvik.find_test

import com.ruvik.domain.testmanagement.data.testinfo.TestBody

interface FindTestRouter {
    fun goToMyTests()
    fun goToRunTest(testBody: TestBody)
}