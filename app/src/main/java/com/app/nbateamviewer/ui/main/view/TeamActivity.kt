package com.app.nbateamviewer.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nbateamviewer.R
import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.ui.main.adapter.TeamDetailAdapter
import kotlinx.android.synthetic.main.activity_team.*

/**
 * TODO
 * Team activity which contains team detail and players list screen functionality
 */
class TeamActivity : AppCompatActivity() {

    private lateinit var adapter: TeamDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        rv_detail_players.layoutManager = LinearLayoutManager(this)
        adapter = TeamDetailAdapter(arrayListOf(), this)
        rv_detail_players.adapter = adapter

        if (intent.hasExtra("team")) {
            val team = intent.getParcelableExtra<Team>("team")

            tv_detail_team.text = team.fullName
            tv_detail_win.text=team.wins.toString()
            tv_detail_loss.text=team.losses.toString()

            adapter.apply {
                addPlayers(team.players)
            }
        }

        iv_back.setOnClickListener {
            finish()
        }
    }

}
