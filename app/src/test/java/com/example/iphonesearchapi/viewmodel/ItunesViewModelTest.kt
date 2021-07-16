package com.example.iphonesearchapi.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.iphonesearchapi.getOrAwaitValue
import com.example.iphonesearchapi.model.ITunesResponse
import com.example.iphonesearchapi.model.Result
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import io.mockk.*
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Response


class ViewModelTest {
    private lateinit var apiService: IphoneApiService
    private lateinit var result: List<Result>
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
private lateinit var  a :Result

    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    @Before
    fun setup() {
        a= mockk()
        apiService = mockk()
        result=mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun suspendfunctest_retrofitResponse() {
        runBlockingTest {
            coEvery { apiService.getResult("") } returns Response.success(
                ITunesResponse(emptyList())
            )
            val viewModel = ItunesViewModel(apiService)
            ItunesViewModel(apiService).triggerItunesapi("")
            viewModel.itunes.getOrAwaitValue()
        }
    }

    @After
    fun tearDown() {testCoroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}