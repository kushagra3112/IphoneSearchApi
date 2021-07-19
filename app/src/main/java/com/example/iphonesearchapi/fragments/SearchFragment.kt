package com.example.iphonesearchapi.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.FragmentSearchBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel: ItunesViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding

    private val itunesAdapter: ItunesAdapter by lazy {
        ItunesAdapter(mutableListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.recyclerView.adapter = itunesAdapter
        viewModel.itunes.observe(viewLifecycleOwner, { resourse ->
            when (resourse) {
                is ResultOf.Loading -> {
                    showLoadingIndicator(true, binding.progressBar)
                }
                is ResultOf.Failure -> {
                }
                is ResultOf.Success -> {
                    showLoadingIndicator(false, binding.progressBar)
                    binding.recyclerView.visibility = View.VISIBLE
                    itunesAdapter.resetDataSource(resourse.value)
                }
            }
        })
        binding.searchCityEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    binding.recyclerView.visibility = View.INVISIBLE
                } else {
                    viewModel.triggerItunesapi(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        return binding.root
    }

}

