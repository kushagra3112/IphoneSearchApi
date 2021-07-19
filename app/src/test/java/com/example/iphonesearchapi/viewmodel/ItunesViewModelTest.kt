package com.example.iphonesearchapi.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.iphonesearchapi.getOrAwaitValue
import com.example.iphonesearchapi.model.ITunesResponse
import com.example.iphonesearchapi.model.ResultOf
import com.example.iphonesearchapi.network.IphoneApiService
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.rules.TestRule
import retrofit2.Response


class ViewModelTest {

    private lateinit var apiService: IphoneApiService
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    private lateinit var viewModel: ItunesViewModel

    @Before
    fun setup() {
        apiService = mockk()
        Dispatchers.setMain(testDispatcher)

        viewModel = ItunesViewModel(apiService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun suspendfunctest_retrofitResponse() {
        runBlockingTest {
            coEvery { apiService.getResult("") } returns Response.success(
                ITunesResponse(emptyList())
            )
            viewModel.triggerItunesapi("")
            val result = viewModel.itunes.getOrAwaitValue()
            assertThat(result, `is`(ResultOf.Loading))

        }
    }

    @After
    fun tearDown() {
        testCoroutineScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}