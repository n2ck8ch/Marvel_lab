package com.example.marvellab

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponse(
    val data: DataContainer
)

@JsonClass(generateAdapter = true)
data class DataContainer(
    val results: List<Character>
)

@JsonClass(generateAdapter = true)
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    val path: String,
    val extension: String
) {
    val fullUrl: String
        get() = "$path.$extension"
}
