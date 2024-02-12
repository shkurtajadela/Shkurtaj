package com.adelashkurtaj.shkurtaj.domain

import com.adelashkurtaj.shkurtaj.domain.models.Film
import kotlinx.coroutines.flow.Flow

interface FavoriteFilmsRepository {

    fun getFavoriteFilmsFlow(): Flow<List<Film>>

    suspend fun getFavoriteFilms(): List<Film>

    suspend fun deleteFavoriteFilm(filmId: Int)

    suspend fun addFavoriteFilm(film: Film)

    suspend fun searchFavoriteFilms(query: String): List<Film>
}