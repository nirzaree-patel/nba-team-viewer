package com.app.nbateamviewer.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.nbateamviewer.data.model.Team
import com.app.nbateamviewer.data.repository.MainRepository
import com.app.nbateamviewer.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher

/**
 * TODO
 * This is a viewmodel class to handle the API response and emits the data to MainActivity.
 * @property mainRepository
 * @property ioDispatcher
 */
class MainViewModel(
    private val mainRepository: MainRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val teamList: LiveData<Resource<List<Team>>> = liveData(ioDispatcher) {
        emit(Resource.loading(data = null))
        try {
            var result = mainRepository.getTeam()
            result = result.sortedBy { it.fullName }
            emit(Resource.success(data = result))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: exception.toString()))
        }
    }
}
