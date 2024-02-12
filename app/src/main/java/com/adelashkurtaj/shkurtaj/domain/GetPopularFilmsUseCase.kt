package com.adelashkurtaj.shkurtaj.domain

import com.adelashkurtaj.shkurtaj.domain.models.Film

class GetPopularFilmsUseCase(
    private val filmsRepository: FilmsRepository,
    private val favoriteFilmsRepository: FavoriteFilmsRepository
) {
    suspend operator fun invoke(): Result<List<Film>> {
        val favoriteFilms = favoriteFilmsRepository.getFavoriteFilms()
        val popularFilmsResult = filmsRepository.getPopularFilms()

        return popularFilmsResult.getOrNull()?.let { films ->
            val resultList = films.map { popularFilm ->
                if (favoriteFilms.find { popularFilm.id == it.id } != null) {
                    popularFilm.copy(
                        isFavorite = true
                    )
                } else {
                    popularFilm
                }
            }
            Result.success(resultList)
        } ?: Result.failure(exception = popularFilmsResult.exceptionOrNull() ?: Exception())
    }
}