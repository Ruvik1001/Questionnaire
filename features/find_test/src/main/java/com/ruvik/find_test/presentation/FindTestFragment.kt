package com.ruvik.find_test.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.ruvik.find_test.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for finding tests.
 */
class FindTestFragment : Fragment() {

    private val viewModel by viewModel<FindTestViewModel>()

    private lateinit var editTextTestCode: EditText
    private lateinit var buttonFindTest: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageViewNotebook: ImageView
    private lateinit var imageViewClearText: ImageView
    private lateinit var imageViewFacts: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_test, container, false)

        editTextTestCode = view.findViewById(R.id.editTextTestCode)
        buttonFindTest = view.findViewById(R.id.buttonFindTest)
        progressBar = view.findViewById(R.id.progressBar)
        imageViewNotebook = view.findViewById(R.id.imageViewNotebook)
        imageViewClearText = view.findViewById(R.id.imageViewClearText)
        imageViewFacts = view.findViewById(R.id.imageButtonFacts)

        imageViewFacts.setOnClickListener { viewModel.gotToFacts() }


        editTextTestCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                return
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty()) {
                    buttonFindTest.isClickable = false
                    imageViewClearText.visibility = ImageView.GONE
                }
                else {
                    buttonFindTest.isClickable = true
                    imageViewClearText.visibility = ImageView.VISIBLE
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                return
            }
        })

        imageViewClearText.setOnClickListener {
            editTextTestCode.clearFocus()
            editTextTestCode.text.clear()
            hideInputBoard()
        }

        imageViewNotebook.setOnClickListener {
            viewModel.goToMyTests()
        }

        buttonFindTest.setOnClickListener {
            hideInputBoard()
            progressBar.visibility = ProgressBar.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val url = editTextTestCode.text.toString()
                val result = viewModel.findTest(url)
                withContext(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.GONE
                    if (url.isNotBlank() && result)
                        AlertDialog.Builder(this@FindTestFragment.requireContext())
                            .setTitle(getString(R.string.title_find_test))
                            .setMessage(
                                getString(
                                    R.string.test_find_successful,
                                    viewModel.getMetadata().first,
                                    viewModel.getMetadata().second
                                )
                            )
                            .setPositiveButton(getString(R.string.start_test)) { _, _ -> viewModel.runTest() }
                            .setNegativeButton(getString(R.string.brake_test), null)
                            .create()
                            .show()
                    else
                        AlertDialog.Builder(this@FindTestFragment.requireContext())
                            .setTitle(getString(R.string.title_find_test))
                            .setMessage(getString(R.string.test_find_failure))
                            .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                                buttonFindTest.performClick() }
                            .setNegativeButton(getString(R.string.confirm_ok), null)
                            .create()
                            .show()
                }
            }
        }
        buttonFindTest.isClickable = false
        return view
    }

    private fun hideInputBoard() {
        val inputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editTextTestCode.windowToken, 0)
    }
}
