package com.cianjansen.spotifygame.data.repository

import com.cianjansen.spotifygame.data.api.ApiHelper
import com.cianjansen.spotifygame.data.model.Artist
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {
    fun getArtists(): Single<List<Artist>> {
        return apiHelper.getArtists()
    }
}