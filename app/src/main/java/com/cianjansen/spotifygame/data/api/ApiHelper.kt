package com.cianjansen.spotifygame.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getArtists() = apiService.getArtists()

    fun getSongs() = apiService.getSongs()
}