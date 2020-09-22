package com.app.nbateamviewer.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getTeam() = apiService.getTeam()
}