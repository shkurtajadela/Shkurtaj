package com.adelashkurtaj.shkurtaj.domain

import com.adelashkurtaj.shkurtaj.domain.models.Film
import com.adelashkurtaj.shkurtaj.domain.models.FilmDetails


interface FilmsRepository {
    suspend fun getPopularFilms(): Result<List<Film>>

    suspend fun getFilmDetails(filmId: Int): Result<FilmDetails>

    suspend fun searchFilmByKeyword(keyword: String): Result<List<Film>>
}