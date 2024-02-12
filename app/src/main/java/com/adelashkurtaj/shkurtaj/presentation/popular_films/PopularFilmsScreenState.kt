package com.adelashkurtaj.shkurtaj.presentation.popular_films

import com.adelashkurtaj.shkurtaj.presentation.model.FilmUi


sealed interface PopularFilmsScreenState {

    data object Loading: PopularFilmsScreenState

    data class Content(
        val films: List<FilmUi>
    ) : PopularFilmsScreenState

    data object Error: PopularFilmsScreenState
}
