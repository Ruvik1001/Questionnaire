package com.ruvik.see_results.presentation

import androidx.lifecycle.ViewModel
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import com.ruvik.domain.testmanagement.data.testinfo.TestResult
import com.ruvik.domain.testmanagement.usecase.LoadTestResultsUseCase
import com.ruvik.see_results.data.PresentationResultTestItem
import com.ruvik.see_results.data.ResultTestItem

/**
 * ViewModel for displaying the results of a test.
 *
 * @param loadTestResultsUseCase The use case for loading test results.
 */
class SeeResultsViewModel(
    private val loadTestResultsUseCase: LoadTestResultsUseCase
) : ViewModel() {
    private var resultList: List<TestResult> = listOf()

    /**
     * Loads the results of a test with the specified hash code.
     *
     * @param hashCode The hash code of the test.
     * @return `true` if the results were successfully loaded, `false` otherwise.
     */
    suspend fun loadResults(hashCode: String): Boolean {
        resultList = loadTestResultsUseCase.execute(hashCode)
        return resultList.isNotEmpty()
    }

    /**
     * Maps a [TestItem] to a [ResultTestItem].
     *
     * @param testItem The test item to map.
     * @return A [ResultTestItem] object representing the mapped test item.
     */
    private fun mapToResultTestItem(testItem: TestItem): ResultTestItem = ResultTestItem(
        title = testItem.title,
        variants = testItem.variants,
        answers = testItem.changed.toMutableList()
    )

    /**
     * Gets a list of [ResultTestItem] objects from the loaded test results.
     *
     * @return A list of [ResultTestItem] objects.
     */
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

    /**
     * Counts the occurrences of each number in the given list and returns a list of pairs
     * representing the number and its count.
     *
     * @param list The list of numbers.
     * @return A list of pairs representing the number and its count.
     */
    private fun countNum(list: MutableList<Int>): List<Pair<Int, Int>> {
        val numCountMap = mutableMapOf<Int, Int>()

        for (num in list) {
            numCountMap[num] = numCountMap.getOrDefault(num, 0) + 1
        }

        return numCountMap.toList()
    }

    /**
     * Gets a list of presentation items representing the results of the test.
     *
     * @return A list of [PresentationResultTestItem] objects.
     */
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
