package com.ruvik.find_test.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_test, container, false)

        editTextTestCode = view.findViewById(R.id.editTextTestCode)
        buttonFindTest = view.findViewById(R.id.buttonFindTest)
        progressBar = view.findViewById(R.id.progressBar)
        imageViewNotebook = view.findViewById(R.id.imageViewNotebook)

        imageViewNotebook.setOnClickListener {
            viewModel.goToMyTests()
        }

        buttonFindTest.setOnClickListener {
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
                            .setPositiveButton(getString(R.string.confirm_ok), null)
                            .create()
                            .show()
                }
            }
        }
        return view
    }
}
