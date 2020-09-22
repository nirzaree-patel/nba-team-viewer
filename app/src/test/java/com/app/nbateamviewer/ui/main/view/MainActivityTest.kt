package com.app.nbateamviewer.ui.main.view

import com.app.nbateamviewer.buildUser
import com.app.nbateamviewer.data.model.Team
import org.junit.Assert
import org.junit.Test

class MainActivityTest {

    @Test
    fun sortTeamsAscending() {
        //Given
        val teams = ArrayList<Team>()
        teams.add(buildUser(60, 20, "Houston Rockets"))
        teams.add(buildUser(50, 30, "Golden State Warriors"))
        teams.add(buildUser(40, 40, "Toronto Raptors"))
        teams.add(buildUser(50, 30, "Milwaukee Bucks"))
        teams.add(buildUser(20, 60, "Brooklyn Nets"))
        //When
        val sortedList = teams.sortedBy { it.fullName }
        //Then
        Assert.assertEquals("Brooklyn Nets", sortedList[0].fullName)
        Assert.assertEquals("Toronto Raptors", sortedList[sortedList.size - 1].fullName)
    }

    @Test
    fun sortTeamsDescending() {
        //Given
        val teams = ArrayList<Team>()
        teams.add(buildUser(60, 20, "Houston Rockets"))
        teams.add(buildUser(50, 30, "Golden State Warriors"))
        teams.add(buildUser(40, 40, "Toronto Raptors"))
        teams.add(buildUser(50, 30, "Milwaukee Bucks"))
        teams.add(buildUser(20, 60, "Brooklyn Nets"))
        //When
        val sortedList = teams.sortedBy { it.fullName }.reversed()
        //Then
        Assert.assertEquals("Toronto Raptors", sortedList[0].fullName)
        Assert.assertEquals("Brooklyn Nets", sortedList[sortedList.size - 1].fullName)
    }

    @Test
    fun sortTeamsByMostWins() {
        //Given
        val teams = ArrayList<Team>()
        teams.add(buildUser(60, 20, "Houston Rockets"))
        teams.add(buildUser(50, 30, "Golden State Warriors"))
        teams.add(buildUser(40, 40, "Toronto Raptors"))
        teams.add(buildUser(50, 30, "Milwaukee Bucks"))
        teams.add(buildUser(20, 60, "Brooklyn Nets"))
        //When
        val sortedList = teams.sortedBy { it.wins }
        //Then
        Assert.assertEquals("Brooklyn Nets", sortedList[0].fullName)
        Assert.assertEquals("Houston Rockets", sortedList[sortedList.size - 1].fullName)
    }

    @Test
    fun sortTeamsByMostLosses() {
        //Given
        val teams = ArrayList<Team>()
        teams.add(buildUser(60, 20, "Houston Rockets"))
        teams.add(buildUser(50, 30, "Golden State Warriors"))
        teams.add(buildUser(40, 40, "Toronto Raptors"))
        teams.add(buildUser(50, 30, "Milwaukee Bucks"))
        teams.add(buildUser(20, 60, "Brooklyn Nets"))
        //When
        val sortedList = teams.sortedBy { it.losses }
        //Then
        Assert.assertEquals("Houston Rockets", sortedList[0].fullName)
        Assert.assertEquals("Brooklyn Nets", sortedList[sortedList.size - 1].fullName)
    }
}