package com.example.roomdb.busInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdb.database.AppDatabase
import com.example.roomdb.entities.Timing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TimingRepository

    init {
        val articleDao = AppDatabase.getDatabase(application).timingDao()
        repository = TimingRepository(articleDao)
    }

    private val _timings = MutableLiveData<List<Timing>>()
    val timings: LiveData<List<Timing>> get() = _timings

    fun getAllTimings() {
        viewModelScope.launch {
            _timings.value = repository.getAllTimings()
        }
    }

    fun insertTimings(articles: List<Timing>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTimings(articles)
        }
    }

    fun clearTimings() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearTimings()
        }
    }
}