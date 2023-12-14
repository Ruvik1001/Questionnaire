package com.ruvik.see_results.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.ruvik.see_results.R
import com.ruvik.see_results.data.PresentationResultTestItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for see test results.
 */
class SeeResultsFragment : Fragment() {
    private val viewModel by viewModel<SeeResultsViewModel>()
    private lateinit var view: View

    private lateinit var testContainer: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewNoResults: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_see_result, container, false)

        testContainer = view.findViewById(R.id.test_container)
        progressBar = view.findViewById(R.id.progressBar)
        textViewNoResults = view.findViewById(R.id.textViewNoResults)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.loadResults(requireArguments().getString(resources.getString(com.ruvik.common.R.string.key_test_hash_translation_by_bundle))!!)
            withContext(Dispatchers.Main) {
                progressBar.visibility = ProgressBar.GONE
                val itemList = viewModel.getPresentationResultTestItemList()
                if (itemList.isEmpty())
                    textViewNoResults.visibility = TextView.VISIBLE
                for (elem in itemList)
                    testContainer.addView(getNewItem(elem))
            }
        }

        return view
    }

    private fun getNewItem(presentationResultTestItem: PresentationResultTestItem): View {
        val itemLayout = layoutInflater.inflate(R.layout.test_item_radio, testContainer, false)

        val title: TextView = itemLayout.findViewById(R.id.question_name)
        val group: LinearLayout = itemLayout.findViewById(R.id.group)

        title.text = presentationResultTestItem.title

        val countItem = presentationResultTestItem.variants.size
        val ansMap = presentationResultTestItem.answers.toMap()
        for (i in 0 until countItem) {
            var countAns = 0
            if (ansMap[i] != null)
                countAns = ansMap[i]!!

            val textView = TextView(requireContext()).apply {
                text = context.getString(
                    R.string.was_pushed_pattern,
                    countAns.toString(),
                    presentationResultTestItem.variants[i]
                )
                setTextColor(resources.getColor(com.ruvik.common.R.color.black))
                textSize = 18f
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            group.addView(textView)
        }

        return itemLayout
    }
}
