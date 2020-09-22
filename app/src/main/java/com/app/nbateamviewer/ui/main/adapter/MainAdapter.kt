package com.app.nbateamviewer.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.nbateamviewer.R
import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.ui.main.adapter.MainAdapter.DataViewHolder
import com.app.nbateamviewer.ui.main.view.TeamActivity
import kotlinx.android.synthetic.main.item_team_list.view.*
import java.util.*

/**
 * TODO
 * This recyclerview adapter class is used to display the list of team.
 * @property teamList array list of all team objects coming from API response
 * @property context context reference to main activity to use in image dialog display
 */
class MainAdapter(private val teamList: ArrayList<Team>, val context: Context) :
    RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * TODO
         * main function to bind all the views of row in recyclerview
         * @param team object of team model class
         * @param context context reference to main activity
         */
        fun bind(team: Team, context: Context) {
            itemView.apply {
                if (!team.fullName.isEmpty()) tv_teamitem_name.text =
                    team.fullName else tv_teamitem_name.text = "Anonymous"
                tv_teamitem_win.text = "W - " + team.wins.toString()
                tv_teamitem_loss.text = "L - " + team.losses.toString()

            }

            itemView.setOnClickListener {
                val intent = Intent(context, TeamActivity::class.java)
                intent.putExtra("team", team)
                context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false)
        )

    override fun getItemCount(): Int = teamList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(teamList[position], context)
    }

    /**
     * TODO
     * function to add the list of team after API response
     * @param teamList list of all team objects
     */
    fun addTeams(teamList: List<Team>) {
        this.teamList.apply {
            clear()
            addAll(teamList)
        }
        notifyDataSetChanged()
    }

    fun getTeams(): List<Team> = teamList

}