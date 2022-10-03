package com.cianjansen.spotifygame.data.model
import com.google.gson.annotations.SerializedName

data class Song(
    @SerializedName("album")
    val album: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("plays")
    val plays: Int
)