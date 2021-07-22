package com.example.iphonesearchapi.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.iphonesearchapi.R
import com.google.android.play.core.tasks.Task
import org.junit.Assert.*
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest{
    fun test(){
        val bundle = DetailsFragmentArgs().toBundle()
        launchFragmentInContainer<SearchFragment>(bundle, R.layout.fragment_search)
        Thread.sleep(2000)
    }

}