package com.example.iphonesearchapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.iphonesearchapi.databinding.FragmentDetailBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.detailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {

    private val viewModel: detailViewModel by viewModel()
    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        val args: DetailsFragmentArgs by navArgs()
        val actionbar: ActionBar =  (activity as AppCompatActivity).supportActionBar!!


        actionbar.title =args.songName
        viewModel.triggerItunesapi(args.songId)
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