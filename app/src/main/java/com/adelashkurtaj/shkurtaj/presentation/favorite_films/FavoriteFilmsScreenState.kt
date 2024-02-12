package com.adelashkurtaj.shkurtaj.presentation.favorite_films

import com.adelashkurtaj.shkurtaj.presentation.model.FilmUi

sealed interface FavoriteFilmsScreenState {

    data object Loading: FavoriteFilmsScreenState

    data class Content(
        val favoriteFilms: List<FilmUi>
    ): FavoriteFilmsScreenState

    data object Error: FavoriteFilmsScreenState
}