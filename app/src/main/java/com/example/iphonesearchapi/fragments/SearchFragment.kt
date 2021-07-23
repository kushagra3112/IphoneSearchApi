package com.example.iphonesearchapi.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.database.SongRepository
import com.example.iphonesearchapi.databinding.FragmentSearchBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.CustomApp
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import com.example.iphonesearchapi.viewmodel.SongViewModel
import com.example.iphonesearchapi.viewmodel.SongViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private val viewModel: ItunesViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding

    private val itunesAdapter: ItunesAdapter by lazy {
        ItunesAdapter(mutableListOf())
    }

    private val songViewModel: SongViewModel by viewModels {
        SongViewModelFactory(( activity?.application as CustomApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        songViewModel.observe()
        lifecycleScope.launchWhenStarted {
            songViewModel.loginUserFlow.collect {
                when(it){
                    is ResultOf.Failure -> {
                    }
                    ResultOf.Loading -> {
                    }
                    is ResultOf.Success -> {
                        if (it.value.isNotEmpty()) {
                            itunesAdapter.setfavlist(it.value)
                        }
                    }
                }
            }
        }
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
        binding.searchCityEditText.getQueryText()
            .debounce(300)
            .onEach {
                viewModel.triggerItunesapi(it)
            }
            .launchIn(lifecycleScope)
        return binding.root
    }
    private fun TextInputEditText.getQueryText():StateFlow<String>{
        val flow= MutableStateFlow("")
        addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                flow.value=s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        return flow
    }

}


