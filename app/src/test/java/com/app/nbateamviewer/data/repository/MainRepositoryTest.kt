package com.app.nbateamviewer.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.nbateamviewer.data.api.ApiHelper
import com.app.nbateamviewer.data.api.ApiService
import com.app.nbateamviewer.data.model.Team
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mainRepository: MainRepository
    lateinit var apiHelperMock: ApiHelper
    val apiServiceMock = mock<ApiService>()

    @Before
    fun setUp() {
        apiHelperMock=ApiHelper(apiServiceMock)
        mainRepository = MainRepository(apiHelperMock)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTeamTest() = runBlockingTest{
        val teamList = emptyList<Team>()
        doReturn(teamList).`when`(apiServiceMock).getTeam()
        assertEquals(teamList, mainRepository.getTeam())
    }
}