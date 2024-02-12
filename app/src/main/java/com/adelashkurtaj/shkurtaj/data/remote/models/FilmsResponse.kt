package com.adelashkurtaj.shkurtaj.data.remote.models

import com.google.gson.annotations.SerializedName

data class FilmsResponse(
    @SerializedName("films")
    val films: List<FilmDto>?
)



