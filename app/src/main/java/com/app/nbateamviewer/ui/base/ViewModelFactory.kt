package com.app.nbateamviewer.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.nbateamviewer.data.api.ApiHelper
import com.app.nbateamviewer.data.repository.MainRepository
import com.app.nbateamviewer.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper), Dispatchers.IO) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

