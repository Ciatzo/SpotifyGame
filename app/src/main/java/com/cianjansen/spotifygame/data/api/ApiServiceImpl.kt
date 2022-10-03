package com.cianjansen.spotifygame.data.api

import com.cianjansen.spotifygame.data.model.Artist
import com.cianjansen.spotifygame.data.model.Song
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request


class ApiServiceImpl : ApiService {
    private val client = OkHttpClient()

    override fun getArtists(): Single<List<Artist>> {
        val request = Request.Builder()
            .url("https://631f0dc858a1c0fe9f5ed2d4.mockapi.io/api/v1/artist/")
            .build()

        return try {
            val typeToken = TypeToken.getParameterized(List::class.java, Artist::class.java).type

            Single.create {
                val response = client.newCall(request).execute().body?.string()
                val parsed: List<Artist> = Gson().fromJson(response, typeToken)
                it.onSuccess(parsed)
            }
        } catch (e: Exception) {
            e.printStackTrace()

            Single.error(e)
        }
    }

    override fun getSongs(): Single<List<Song>> {
        val request = Request.Builder()
            .url("https://631f0dc858a1c0fe9f5ed2d4.mockapi.io/api/v1/song/")
            .build()

        return try {
            val typeToken = TypeToken.getParameterized(List::class.java, Song::class.java).type

            Single.create {
                val response = client.newCall(request).execute().body?.string()
                val parsed: List<Song> = Gson().fromJson(response, typeToken)
                it.onSuccess(parsed)
            }
        } catch (e: Exception) {
            e.printStackTrace()

            Single.error(e)
        }
    }
}