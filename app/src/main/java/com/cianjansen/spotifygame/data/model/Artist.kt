package com.cianjansen.spotifygame.data.model

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("href")
    val href: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)