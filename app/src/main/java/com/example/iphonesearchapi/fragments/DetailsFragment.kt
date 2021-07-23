package com.example.iphonesearchapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.database.SongRepository
import com.example.iphonesearchapi.databinding.FragmentDetailBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.CustomApp
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.DetailViewModel
import com.example.iphonesearchapi.viewmodel.SongViewModel
import com.example.iphonesearchapi.viewmodel.SongViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowWith
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding : FragmentDetailBinding
    private val songViewModel: SongViewModel by viewModels {
        SongViewModelFactory(( activity?.application as CustomApp).repository)
    }
    var favlist:List<Long> = listOf()
    val args: DetailsFragmentArgs by navArgs()
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        requireActivity().title =args.songName

        viewModel.triggerItunesapi(args.songId)
        songViewModel.observe()
        binding.toggleButton.setOnCheckedChangeListener{
                _,isChecked->
            if(binding.toggleButton.isPressed()) {
                if(isChecked)
                    songViewModel.insert(args.songId.toLong())
                else
                    songViewModel.delete(args.songId.toLong())
            }

        }
        lifecycleScope.launchWhenStarted {
            songViewModel.loginUserFlow.collect {
                when(it){
                    is ResultOf.Failure -> {
                    }
                    ResultOf.Loading -> {
                    }
                    is ResultOf.Success -> {
                        val isFavorite = it.value.contains(args.songId.toLong())
                        binding.toggleButton.isChecked = if(isFavorite) true else false

                    }
                }
            }
        }
        viewModel.songDetails.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is ResultOf.Loading -> {
                    showLoadingIndicator(true,binding.progressBar2)
                }
                is ResultOf.Failure -> {
                }
                is ResultOf.Success -> {
                    showLoadingIndicator(false,binding.progressBar2)
                    binding.textviewArtistName.setText(resource.value[0].artistName)
                }
            }
        })

        return binding.root
    }
}