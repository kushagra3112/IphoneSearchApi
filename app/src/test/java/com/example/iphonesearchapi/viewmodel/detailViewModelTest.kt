package com.example.iphonesearchapi.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.iphonesearchapi.getOrAwaitValue
import com.example.iphonesearchapi.model.ITunesResponse
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response

class detailViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private lateinit var viewModel: detailViewModel
    private lateinit var apiService: IphoneApiService
    @Before
    fun setup(){
        apiService=mockk()
        Dispatchers.setMain(testDispatcher)
        viewModel= detailViewModel(apiService)
    }

    @Test
    fun `Detail ViewModel Test` (){
        runBlockingTest {
            coEvery {
                apiService.getSongDetail(any())
            } returns Response.success(
                ITunesResponse(emptyList())
            )
            viewModel.triggerItunesapi(1)
            val result=viewModel.songDetails.getOrAwaitValue ()
            assertThat(result, `is`(ResultOf.Success(emptyList())) )

        }
    }
    @After
    fun tearDown(){
        testCoroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

}