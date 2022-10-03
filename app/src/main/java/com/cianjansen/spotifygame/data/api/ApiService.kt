package com.cianjansen.spotifygame.data.api

import com.cianjansen.spotifygame.data.model.Artist
import com.cianjansen.spotifygame.data.model.Song
import io.reactivex.Single

interface ApiService {
    fun getArtists(): Single<List<Artist>>

    fun getSongs(): Single<List<Song>>
}