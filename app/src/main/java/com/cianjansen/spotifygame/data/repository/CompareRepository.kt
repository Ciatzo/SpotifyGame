package com.cianjansen.spotifygame.data.repository

import com.cianjansen.spotifygame.data.api.ApiHelper
import com.cianjansen.spotifygame.data.model.Song
import io.reactivex.Single

class CompareRepository(private val apiHelper: ApiHelper) {
    fun getSongs(): Single<List<Song>> {
        return apiHelper.getSongs()
    }
}