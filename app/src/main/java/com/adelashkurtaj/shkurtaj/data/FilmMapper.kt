package com.adelashkurtaj.shkurtaj.data

import com.adelashkurtaj.shkurtaj.data.local.models.FavoriteFilmEntity
import com.adelashkurtaj.shkurtaj.data.remote.models.CountryDto
import com.adelashkurtaj.shkurtaj.data.remote.models.FilmDetailsResponse
import com.adelashkurtaj.shkurtaj.data.remote.models.FilmDto
import com.adelashkurtaj.shkurtaj.data.remote.models.GenreDto
import com.adelashkurtaj.shkurtaj.domain.models.Film
import com.adelashkurtaj.shkurtaj.domain.models.FilmDetails

class FilmMapper {

    fun mapDomainToFavoriteFilmEntity(film: Film): FavoriteFilmEntity {
        return FavoriteFilmEntity(
            id = film.id,
            title = film.title,
            year = film.year ?: 0,
            genre = film.genre.joinToString(","),
            countries = film.countries.joinToString(","),
            posterUrl = film.posterUrl
        )
    }

    fun mapFavouriteFilmEntityToDomain(entity: FavoriteFilmEntity): Film {
        return Film(
            id = entity.id,
            title = entity.title,
            year = entity.year,
            genre = entity.genre.split(','),
            posterUrl = entity.posterUrl,
            countries = entity.countries.split(','),
            isFavorite = true
        )
    }
    fun mapFilmDetailsDtoToDomain(filmDetailsResponse: FilmDetailsResponse): FilmDetails {
        return FilmDetails(
            id = filmDetailsResponse.id,
            title = filmDetailsResponse.title.orEmpty(),
            year = filmDetailsResponse.year,
            posterUrl = filmDetailsResponse.posterUrl.orEmpty(),
            genre = filmDetailsResponse.genres?.map { it.genre.orEmpty() }.orEmpty(),
            countries = filmDetailsResponse.countries?.map { it.country.orEmpty() }.orEmpty(),
            description = filmDetailsResponse.description.orEmpty()
        )
    }
    fun mapListDtoToDomainList(films: List<FilmDto>): List<Film> {
        return films.map(::mapDtoToDomain)
    }

    private fun mapDtoToDomain(film: FilmDto): Film {
        return Film(
            id = film.filmId,
            title = film.title.orEmpty(),
            year = film.year,
            posterUrl = film.posterUrl.orEmpty(),
            genre = film.genres?.map { it.genre.orEmpty() }.orEmpty(),
            countries = film.countries?.map { it.country.orEmpty() }.orEmpty(),
            isFavorite = false
        )
    }
}