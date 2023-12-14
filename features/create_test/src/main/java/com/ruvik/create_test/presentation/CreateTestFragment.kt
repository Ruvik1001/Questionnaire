package com.ruvik.create_test.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.ruvik.common.LongToast
import com.ruvik.create_test.R
import com.ruvik.domain.testmanagement.data.testinfo.TestItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

/**
 * Fragment for creating a new test.
 */
class CreateTestFragment : Fragment() {

    private val viewModel by inject<CreateTestViewModel>()
    private lateinit var view: View

    private lateinit var testPrevLayout: RelativeLayout
    private lateinit var testNameEditText: EditText
    private lateinit var btnSetTestName: Button

    private lateinit var testBodyLayout: RelativeLayout
    private lateinit var textViewTitle: TextView
    private lateinit var containerLayout: LinearLayout
    private lateinit var btnAddQuestion: Button
    private lateinit var btnConfirm: Button

    private var index = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_create_test, container, false)

        viewModel.onRecreateCatch()

        testPrevLayout = view.findViewById(R.id.test_prev)
        testNameEditText = view.findViewById(R.id.testName)
        btnSetTestName = view.findViewById(R.id.btnSetTestName)

        testBodyLayout = view.findViewById(R.id.test_body)
        textViewTitle = view.findViewById(R.id.textViewTitle)
        containerLayout = view.findViewById(R.id.containerLayout)
        btnAddQuestion = view.findViewById(R.id.btnAddQuestion)
        btnConfirm = view.findViewById(R.id.btnConfirm)

        btnSetTestName.setOnClickListener {
            viewModel.setTestName(testNameEditText.text.toString())
            testPrevLayout.visibility = RelativeLayout.GONE
            testBodyLayout.visibility = RelativeLayout.VISIBLE
            textViewTitle.text = viewModel.getTestName()
        }

        btnAddQuestion.setOnClickListener {
            containerLayout.addView(createItem(), index++)
        }

        btnConfirm.setOnClickListener {
            viewModel.needSave.postValue(true)
            CoroutineScope(Dispatchers.IO).launch {
                delay(100)
                if (!viewModel.addNewTest()) {
                    withContext(Dispatchers.Main) {
                        LongToast.makeToast(
                            this@CreateTestFragment.requireContext().applicationContext,
                            getString(R.string.can_not_save)
                        )
                        viewModel.needSave.postValue(false)
                        viewModel.clearItem()
                    }
                    return@launch
                }
                withContext(Dispatchers.Main) {
                    viewModel.confirm()
                }
            }
        }

        return view
    }

    /**
     * Creates a new item view for the test.
     */
    private fun createItem(): View {
        val itemLayout = layoutInflater.inflate(R.layout.test_item, containerLayout, false)

        var num = 0

        val title: EditText = itemLayout.findViewById(R.id.title_q)
        val container: LinearLayout = itemLayout.findViewById(R.id.container)
        val btnAddOption: Button = itemLayout.findViewById(R.id.btnAddOption)
        val answers = mutableListOf<EditText>()

        btnAddOption.setOnClickListener {
            val editText = EditText(this.requireContext())
            editText.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            editText.hint = getString(R.string.enter_option)
            container.addView(editText, num++)
            answers.add(editText)
        }

        viewModel.needSave.observe(this.requireActivity()) {
            if (!it)
                return@observe
            val answersString = mutableListOf<String>()
            for (elem in answers)
                answersString.add(elem.text.toString())
            viewModel.addItem(
                TestItem(
                    title.text.toString(),
                    0,
                    answersString,
                    mutableListOf()
                )
            )
        }

        return itemLayout
    }
}
