package com.ruvik.run_test.presentation

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.google.gson.Gson
import com.ruvik.common.BaseAlert
import com.ruvik.domain.testmanagement.data.testinfo.TestBody
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import com.ruvik.run_test.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for answering to test.
 */
class RunTestFragment : Fragment() {
    private val viewModel by viewModel<RunTestViewModel>()
    private lateinit var view: View

    private lateinit var textViewTestTitle: TextView
    private lateinit var testContainer: LinearLayout
    private lateinit var buttonSend: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_run_test, container, false)

        viewModel.setTestBody(
            Gson().fromJson(
                requireArguments().getString(
                    resources.getString(com.ruvik.common.R.string.key_test_body_translation_by_bundle)
                ), TestBody::class.java
            )
        )

        val testBody = viewModel.getTestBody()
        BaseAlert.showAlert(
            this.requireContext(),
            getString(R.string.alert_about_test_title),
            getString(R.string.alert_about_test_body, testBody.name)
        )

        textViewTestTitle = view.findViewById(R.id.textViewTestTitle)
        testContainer = view.findViewById(R.id.test_container)
        buttonSend = view.findViewById(R.id.buttonSend)
        progressBar = view.findViewById(R.id.progressBar)

        textViewTestTitle.text = testBody.name

        viewModel.mustBeClicked = 0
        for (elem in testBody.items) {
            testContainer.addView(getNewItemType0(elem))
            viewModel.mustBeClicked++
        }

        viewModel.nowClicked.observe(this.requireActivity()) {
            if (viewModel.nowClicked.value == viewModel.mustBeClicked)
                buttonSend.visibility = Button.VISIBLE
        }

        buttonSend.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            buttonSend.isEnabled = false
            CoroutineScope(Dispatchers.IO).launch {
                val isSuccess = viewModel.sendAnswer()
                withContext(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.GONE
                    buttonSend.isEnabled = true
                    if (isSuccess) {
                        val alert = AlertDialog.Builder(this@RunTestFragment.requireContext())
                            .setTitle(getString(R.string.test_send_alert_title))
                            .setMessage(getString(R.string.test_send_alert_text_successful))
                            .setPositiveButton(getString(R.string.finish_text)) { _, _ -> viewModel.goBack() }
                            .create()
                        alert.setOnCancelListener { viewModel.goBack() }
                        alert.show()
                    }
                    else
                        AlertDialog.Builder(this@RunTestFragment.requireContext())
                            .setTitle(getString(R.string.test_send_alert_title))
                            .setMessage(getString(R.string.test_send_alert_text_failure))
                            .setPositiveButton(getString(R.string.repeat_text)) { _, _ -> buttonSend.performClick() }
                            .create()
                            .show()
                }
            }
        }


        return view
    }

    private fun getNewItemType0(testItem: TestItem): View {
        val itemLayout = layoutInflater.inflate(R.layout.test_item_radio, testContainer, false)

        val title: TextView = itemLayout.findViewById(R.id.question_name)
        val group: RadioGroup = itemLayout.findViewById(R.id.group)

        title.text = testItem.title

        for ((index, variant) in testItem.variants.withIndex()) {
            val radioButton = RadioButton(requireContext())
            radioButton.text = variant
            radioButton.id = index
            if (testItem.changed.isNotEmpty() && radioButton.id == testItem.changed[0])
                radioButton.isChecked = true
            group.addView(radioButton)
        }

        group.setOnCheckedChangeListener { _, checkedId ->
            if (testItem.changed.isEmpty())
                viewModel.nowClicked.postValue(viewModel.nowClicked.value!! + 1)
            testItem.changed = listOf(checkedId)
        }

        return itemLayout
    }

}