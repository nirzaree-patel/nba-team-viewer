package com.app.nbateamviewer.data.repository

import com.app.nbateamviewer.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper){
    suspend fun getTeam() = apiHelper.getTeam()
}