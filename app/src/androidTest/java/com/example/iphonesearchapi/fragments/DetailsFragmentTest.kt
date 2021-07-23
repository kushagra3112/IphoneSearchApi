package com.example.iphonesearchapi.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.iphonesearchapi.R
import org.junit.Assert.*
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest{
    fun test(){
        // GIVEN - Add active (incomplete) task to the DB
        val a ="aa"
        // WHEN - Details fragment launched to display task
        val bundle = DetailsFragmentArgs("A","a").toBundle()
        launchFragmentInContainer<SearchFragment>(bundle, R.layout.fragment_detail)
        Thread.sleep(2000)
    }
}