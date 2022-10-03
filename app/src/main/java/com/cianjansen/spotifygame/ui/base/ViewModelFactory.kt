package com.cianjansen.spotifygame.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cianjansen.spotifygame.data.api.ApiHelper
import com.cianjansen.spotifygame.data.repository.CompareRepository
import com.cianjansen.spotifygame.data.repository.MainRepository
import com.cianjansen.spotifygame.ui.activity.viewmodel.CompareViewModel
import com.cianjansen.spotifygame.ui.activity.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(MainRepository(apiHelper)) as T
            modelClass.isAssignableFrom(CompareViewModel::class.java) ->
                CompareViewModel(CompareRepository(apiHelper)) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}