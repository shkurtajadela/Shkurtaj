package com.adelashkurtaj.shkurtaj.data.repository

import com.adelashkurtaj.shkurtaj.data.FilmMapper
import com.adelashkurtaj.shkurtaj.data.local.FavoriteFilmDao
import com.adelashkurtaj.shkurtaj.domain.FavoriteFilmsRepository
import com.adelashkurtaj.shkurtaj.domain.models.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteFilmsRepositoryImpl(
    private val favoriteFilmDao: FavoriteFilmDao,
    private val mapper: FilmMapper
) : FavoriteFilmsRepository {
    override fun getFavoriteFilmsFlow(): Flow<List<Film>> {
        return favoriteFilmDao.getFavoriteFilmsFlow().map { listFavoriteFilmEntity ->
            listFavoriteFilmEntity.map {
                mapper.mapFavouriteFilmEntityToDomain(it)
            }
        }
    }

    override suspend fun getFavoriteFilms(): List<Film> {
        val favoriteFilms = favoriteFilmDao.getFavoriteFilms()
        return favoriteFilms?.map {
            mapper.mapFavouriteFilmEntityToDomain(it)
        } ?: emptyList()
    }

    override suspend fun deleteFavoriteFilm(filmId: Int) {
        favoriteFilmDao.deleteFavoriteFilm(filmId = filmId)
    }

    override suspend fun addFavoriteFilm(film: Film) {
        val favoriteFilmEntity = mapper.mapDomainToFavoriteFilmEntity(film = film)
        favoriteFilmDao.putFavoriteFilm(favoriteFilmEntity)
    }

    override suspend fun searchFavoriteFilms(query: String): List<Film> {
        val favoriteFilms = favoriteFilmDao.searchFavoriteFilms(query)
        return favoriteFilms?.map {
            mapper.mapFavouriteFilmEntityToDomain(it)
        } ?: emptyList()
    }
}