package com.app.nbateamviewer.data.api

import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.utils.endPoint
import retrofit2.http.GET

interface ApiService {

    @GET(endPoint)
    suspend fun getTeam(): List<Team>

}