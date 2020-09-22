package com.app.nbateamviewer.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.nbateamviewer.MainCoroutineRule
import com.app.nbateamviewer.data.api.ApiHelper
import com.app.nbateamviewer.data.api.ApiService
import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.data.repository.MainRepository
import com.app.nbateamviewer.observeForTesting
import com.app.nbateamviewer.utils.Status
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doThrow

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var mainViewModel: MainViewModel
    lateinit var mainRepository: MainRepository
    lateinit var apiHelperMock: ApiHelper
    val apiServiceMock = mock<ApiService>()

    @Before
    fun setUp() {
        apiHelperMock=ApiHelper(apiServiceMock)
        mainRepository = MainRepository(apiHelperMock)
        mainViewModel = MainViewModel(mainRepository, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun testFetchTeamList() = mainCoroutineRule.runBlockingTest {
        //Given
        val teamList = emptyList<Team>()
        doReturn(teamList).`when`(apiServiceMock).getTeam()

        //When
        val result = mainViewModel.teamList
        result.observeForTesting {
            //Then
            assertEquals(Status.SUCCESS, result.value?.status)
            assertEquals(teamList, result.value?.data)
        }
    }

    @Test
    fun testFetchTeamListError() = mainCoroutineRule.runBlockingTest {
        //Given
        val exception = RuntimeException()
        doThrow(exception).`when`(apiServiceMock).getTeam()
        val result = mainViewModel.teamList

        //When
        result.observeForTesting {
            //Then
            assertEquals(Status.ERROR, result.value?.status)
            assertEquals(exception.message ?: exception.toString(), result.value?.message)
        }
    }
}
