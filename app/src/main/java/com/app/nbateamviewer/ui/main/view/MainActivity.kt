package com.app.nbateamviewer.ui.main.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.nbateamviewer.R
import com.app.nbateamviewer.data.api.ApiHelper
import com.app.nbateamviewer.data.api.RetrofitBuilder
import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.ui.base.ViewModelFactory
import com.app.nbateamviewer.ui.main.adapter.MainAdapter
import com.app.nbateamviewer.ui.main.viewmodel.MainViewModel
import com.app.nbateamviewer.utils.Status.*
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TODO
 * Main activity which contains team list screen functionality
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService)))
                .get(MainViewModel::class.java)
    }

    private fun setupUI() {
        toolbar_top.overflowIcon =
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_filter)
        setSupportActionBar(findViewById(R.id.toolbar_top))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        rv_team_list.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this)
        rv_team_list.adapter = adapter
    }

    private fun setupObservers() {

        viewModel.teamList.observe(this, Observer {

            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        rv_team_list.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                        resource.data?.let { teamList -> retrieveList(teamList) }

                    }
                    ERROR -> {
                        rv_team_list.visibility = View.VISIBLE
                        progress.visibility = View.GONE
                        Toast.makeText(this, getString(R.string.error_text), Toast.LENGTH_LONG)
                            .show()
                    }
                    LOADING -> {
                        rv_team_list.visibility = View.GONE
                        progress.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    /**
     * TODO
     * function to retrive the list of all team objects and apply it on adapter after API response.
     * @param teamList list of all team objects
     */
    private fun retrieveList(teamList: List<Team>) {
        if (teamList.size > 0) {
            adapter.apply {
                addTeams(teamList)
            }
        } else {
            Toast.makeText(this, getString(R.string.empty_text), Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.filter_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.sort_by_ascending -> {
                adapter.apply {
                    addTeams(adapter.getTeams().sortedBy { it.fullName })
                }
                true
            }
            R.id.sort_by_descending -> {
                adapter.apply {
                    addTeams(adapter.getTeams().sortedBy { it.fullName }.reversed())
                }
                true
            }
            R.id.sort_by_wins -> {
                adapter.apply {
                    addTeams(adapter.getTeams().sortedBy { it.wins })
                }
                true
            }
            R.id.sort_by_losses -> {
                adapter.apply {
                    addTeams(adapter.getTeams().sortedBy { it.losses })
                }
                true
            }
            else -> {
                true
            }
        }
    }

}
