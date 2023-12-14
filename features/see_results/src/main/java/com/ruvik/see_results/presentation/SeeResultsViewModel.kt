package com.ruvik.see_results.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import com.ruvik.domain.testmanagement.usecase.LoadTestResultsUseCase
import com.ruvik.see_results.data.PresentationResultTestItem
import com.ruvik.see_results.data.ResultTestItem

class SeeResultsViewModel(
    private val loadTestResultsUseCase: LoadTestResultsUseCase
) : ViewModel() {
    private var resultList: List<TestResult> = listOf()

    suspend fun loadResults(hashCode: String): Boolean {
        resultList = loadTestResultsUseCase.execute(hashCode)
        return resultList.isNotEmpty()
    }

    fun getResult() = resultList

    private fun mapToResultTestItem(testItem: TestItem): ResultTestItem = ResultTestItem(
        title = testItem.title,
        variants = testItem.variants,
        answers = testItem.changed.toMutableList()
    )

    private fun getResultTestItemList(): List<ResultTestItem> {
        val resultMap = mutableMapOf<String, ResultTestItem>()

        for (testResult in resultList) {
            for (item in testResult.answers) {
                val key = item.title
                val resultTestItem = resultMap[key]

                if (resultTestItem != null) {
                    resultTestItem.answers.addAll(item.changed)
                } else {
                    resultMap[key] = mapToResultTestItem(item)
                }
            }
        }

        return resultMap.values.toList()
    }

    private fun countNum(list: MutableList<Int>): List<Pair<Int, Int>> {
        val numCountMap = mutableMapOf<Int, Int>()

        for (num in list) {
            numCountMap[num] = numCountMap.getOrDefault(num, 0) + 1
        }

        return numCountMap.toList()
    }

    fun getPresentationResultTestItemList(): List<PresentationResultTestItem> {
        val result = mutableListOf<PresentationResultTestItem>()
        for (elem in getResultTestItemList()) {

            result.add(
                PresentationResultTestItem(
                    title = elem.title,
                    variants = elem.variants,
                    answers = countNum(elem.answers).toMutableList()
                )
            )
        }
        return result
    }
}
