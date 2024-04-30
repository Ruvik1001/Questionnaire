package com.ruvik.find_interesting.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.find_interesting.R
import com.ruvik.common.LongToast
import com.ruvik.find_interesting.adapter.HistoryAdapter
import com.ruvik.find_interesting.adapter.PostAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FindInterestingFragment : Fragment() {
    private val viewModel by viewModel<FindInterestingViewModel>()

    private lateinit var editTextFactHaveWord: EditText
    private lateinit var imageButtonFindFact: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var imageViewClearText: ImageView
    private lateinit var imageViewBack: ImageView
    private lateinit var recyclerViewPublications: RecyclerView

    private lateinit var historyLayout: LinearLayout
    private lateinit var historyRV: RecyclerView
    private lateinit var historyClearButton: ImageView

    private val handler = Handler(Looper.getMainLooper())
    private val delayMillis: Long = 2000 // 2 seconds

    private val searchRunnable = Runnable {
        if (!editTextFactHaveWord.text.isNullOrEmpty()) {
            viewModel.find(editTextFactHaveWord.text.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_interesting, container, false)

        editTextFactHaveWord = view.findViewById(R.id.editTextFactHaveWord)
        imageButtonFindFact = view.findViewById(R.id.buttonFindFact)
        progressBar = view.findViewById(R.id.progressBar)
        imageViewClearText = view.findViewById(R.id.imageViewClearText)
        imageViewBack = view.findViewById(R.id.imageButtonBack)
        recyclerViewPublications = view.findViewById(R.id.rvFacts)

        historyLayout = view.findViewById(R.id.llHistory)
        historyRV = view.findViewById(R.id.rvHistory)
        historyClearButton = view.findViewById(R.id.imageButtonClearHistory)

        recyclerViewPublications.layoutManager = LinearLayoutManager(this.requireContext())
        historyRV.layoutManager = LinearLayoutManager(this.requireContext())

        imageViewBack.setOnClickListener { requireActivity().onBackPressed() }

        editTextFactHaveWord.setOnFocusChangeListener { _, inFocus ->
            if (inFocus) {
                val historyItem = getSearchHistoryFromSP()
                if (!historyItem.isNullOrEmpty())
                    historyLayout.visibility = View.VISIBLE

                historyRV.adapter = HistoryAdapter(historyItem?: listOf()) {
                    editTextFactHaveWord.setText(it)
                    editTextFactHaveWord.clearFocus()
                }
            }
            else historyLayout.visibility = View.GONE
        }

        editTextFactHaveWord.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    imageViewClearText.visibility = View.GONE
                    imageButtonFindFact.isEnabled = false
                } else {
                    imageViewClearText.visibility = View.VISIBLE
                    imageButtonFindFact.isEnabled = true
                }
                handler.removeCallbacks(searchRunnable)
                if (!s.isNullOrEmpty()) {
                    handler.postDelayed(searchRunnable, delayMillis)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        imageViewClearText.setOnClickListener {
            editTextFactHaveWord.clearFocus()
            editTextFactHaveWord.text.clear()
            recyclerViewPublications.adapter = null
            hideInputBoard()
        }

        imageButtonFindFact.setOnClickListener {
            recyclerViewPublications.adapter = null
            editTextFactHaveWord.clearFocus()
            viewModel.find(editTextFactHaveWord.text.toString())
            addSearch(editTextFactHaveWord.text.toString())
            hideInputBoard()
        }

        historyClearButton.setOnClickListener {
            saveSearchHistoryInSP(mutableListOf())
            historyLayout.visibility = View.GONE
        }

        viewModel.postLoaded.observe(this.requireActivity()) {
            when(viewModel.postLoaded.value) {
                FindInterestingViewModel.Status.IN_PROGRESS -> {
                    progressBar.visibility = View.VISIBLE
                }

                else -> progressBar.visibility = View.GONE
            }

            when (viewModel.postLoaded.value) {
                FindInterestingViewModel.Status.SUCCESS_RESPONSE -> {
                    recyclerViewPublications.adapter = PostAdapter(viewModel.getData())
                }

                FindInterestingViewModel.Status.EMPTY_RESPONSE -> LongToast.makeToast(this.requireContext(),
                    getString(
                        R.string.nothing_finded
                    ))

                FindInterestingViewModel.Status.BAD_RESPONSE -> AlertDialog
                    .Builder(this.requireContext())
                    .setTitle(getString(R.string.title_find_error))
                    .setMessage(getString(R.string.server_error))
                    .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                        imageButtonFindFact.performClick() }
                    .setNegativeButton(getString(R.string.confirm_ok), null)
                    .create()
                    .show()

                FindInterestingViewModel.Status.FAILURE_RESPONSE -> AlertDialog
                    .Builder(this.requireContext())
                    .setTitle(getString(R.string.title_find_error))
                    .setMessage(getString(R.string.connection_error))
                    .setPositiveButton(getString(R.string.try_again)) { _, _ ->
                        imageButtonFindFact.performClick() }
                    .setNegativeButton(getString(R.string.confirm_ok), null)
                    .create()
                    .show()

                else -> {}
            }
        }

        return view
    }

    private fun hideInputBoard() {
        val inputMethodManager = requireContext()
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(editTextFactHaveWord.windowToken, 0)
    }

    private fun addSearch(searchText: String) {
        val spList = getSearchHistoryFromSP()?.toMutableList() ?: mutableListOf()
        if (spList.contains(searchText.trim())) {
            spList.removeAt(spList.indexOf(searchText.trim()))
        }
        spList.add(0, searchText.trim())
        saveSearchHistoryInSP(spList)
    }

    private fun getSearchHistoryFromSP(): List<String>? {
        val sharedPreferences = requireContext().getSharedPreferences(SEARCH_SP, Context.MODE_PRIVATE)
        return sharedPreferences.getString(SEARCH_SP_KEY, null)?.split(",")?.toList()
    }

    private fun saveSearchHistoryInSP(history: List<String>) {
        val sharedPreferences = requireContext().getSharedPreferences(SEARCH_SP, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val trimmedHistory = history.take(MAX_HISTORY)
        var resultString: String? = ""
        trimmedHistory.forEach {
            resultString += if (resultString!!.isNotEmpty()) ",$it" else it
        }
        if (resultString!!.isBlank()) resultString = null
        editor.putString(SEARCH_SP_KEY, resultString)
        editor.apply()
    }


    companion object {
        private const val MAX_HISTORY = 10
        private const val SEARCH_SP = "SEARCH_HISTORY_SHARED_NAME"
        private const val SEARCH_SP_KEY = "SEARCH_HISTORY_SHARED_KEY"
    }

}
