package com.adelashkurtaj.shkurtaj.data.remote.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FilmDetailsResponse(
    @SerializedName("kinopoiskId")
    val id: Int,
    @SerializedName("nameRu")
    val title: String?,
    @SerializedName("posterUrl")
    val posterUrl: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("genres")
    val genres: List<GenreDto>?,
    @SerializedName("countries")
    val countries: List<CountryDto>?,
    @SerializedName("year")
    val year: Int?
): Serializable