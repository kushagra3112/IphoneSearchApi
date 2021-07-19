package com.example.iphonesearchapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import com.example.iphonesearchapi.adapter.ItunesAdapter
import com.example.iphonesearchapi.databinding.ActivityMainBinding
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.utility.showLoadingIndicator
import com.example.iphonesearchapi.viewmodel.ItunesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}