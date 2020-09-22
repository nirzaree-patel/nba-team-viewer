package com.app.nbateamviewer.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.nbateamviewer.R
import com.app.nbateamviewer.data.model.Player
import com.app.nbateamviewer.data.model.Team
import kotlinx.android.synthetic.main.item_player_list.view.*

/**
 * TODO
 * This recyclerview adapter class is used to display the list of player.
 * @property playerList array list of all player objects from team object.
 * @property context context reference to main activity to use in image dialog display
 */
class TeamDetailAdapter(private val playerList: ArrayList<Player>, val context: Context) :
    RecyclerView.Adapter<TeamDetailAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /**
         * TODO
         * main function to bind all the views of row in recyclerview
         * @param player object of player model class
         * @param context context reference to team activity
         */
        fun bind(player: Player, context: Context) {
            itemView.apply {
                tv_detailitem_name.text = context.getString(R.string.player_name, player.firstName, player.lastName)
                tv_detailitem_pos_num.text = context.getString(R.string.player_position_number, player.position, player.number)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_player_list, parent, false)
        )

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(playerList[position], context)
    }

    /**
     * TODO
     * function to add the list of player after API response
     * @param playerList list of all player objects
     */
    fun addPlayers(playerList: List<Player>) {
        this.playerList.apply {
            clear()
            addAll(playerList)
        }
        notifyDataSetChanged()
    }

}