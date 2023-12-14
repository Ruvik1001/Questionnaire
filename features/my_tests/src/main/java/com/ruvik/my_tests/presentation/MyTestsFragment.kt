package com.ruvik.my_tests.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ruvik.common.DataTime
import com.ruvik.common.LongToast
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.my_tests.R
import org.koin.android.ext.android.inject

/**
 * Fragment for see your tests.
 */
class MyTestsFragment : Fragment() {
    private val viewModel by inject<MyTestsViewModel>()
    private lateinit var view: View

    private lateinit var textViewMyTestsTitle: TextView
    private lateinit var scrollViewTests: ScrollView
    private lateinit var progressBar: ProgressBar
    private lateinit var testsContainer: LinearLayout
    private lateinit var textViewNoTests: TextView
    private lateinit var navigationPanel: LinearLayout
    private lateinit var imageViewNotebook: ImageView
    private lateinit var imageViewSearch: ImageView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_my_tests, container, false)

        viewModel.load()

        progressBar = view.findViewById(R.id.progressBar)
        testsContainer = view.findViewById(R.id.tests_container)
        textViewNoTests = view.findViewById(R.id.textViewNoTests)
        imageViewNotebook = view.findViewById(R.id.imageViewNotebook)
        imageViewSearch = view.findViewById(R.id.imageViewSearch)
        floatingActionButton = view.findViewById(R.id.imageButtonAdd)

        progressBar.visibility = ProgressBar.VISIBLE
        textViewNoTests.visibility = TextView.VISIBLE

        viewModel.tests.observe(this.requireActivity()) {tests ->
            testsContainer.removeAllViews()

            progressBar.visibility = ProgressBar.GONE
            if (!tests.isEmpty()) {
                textViewNoTests.visibility = TextView.GONE
                for (item in tests) {
                    testsContainer.addView(createItem(item))
                }
            }
        }

        imageViewSearch.setOnClickListener {
            viewModel.goToFindTest()
        }
        floatingActionButton.setOnClickListener {
            viewModel.goToCreateNewTest()
        }

        return view
    }

    private fun createItem(test: TestBody): View {
        val itemLayout = layoutInflater.inflate(R.layout.item_my_test, testsContainer, false)
        val tvTitle = itemLayout.findViewById<TextView>(R.id.test_name)
        tvTitle.text = test.name
        tvTitle.setOnClickListener {
            viewModel.goToSeeTestResults(test.hashCode)
        }
        itemLayout.findViewById<ImageView>(R.id.button_copy).setOnClickListener {
            val clipboardManager =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("TestHashCode", test.hashCode.toString())
            clipboardManager.setPrimaryClip(clipData)
            LongToast.makeToast(this@MyTestsFragment.requireContext().applicationContext, getString(
                R.string.data_copy_successful
            ))
        }
        return itemLayout
    }
}