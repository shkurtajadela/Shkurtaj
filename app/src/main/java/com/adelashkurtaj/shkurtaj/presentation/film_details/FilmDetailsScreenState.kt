package com.adelashkurtaj.shkurtaj.presentation.film_details

import com.adelashkurtaj.shkurtaj.presentation.model.FilmDetailsUi
import com.adelashkurtaj.shkurtaj.presentation.model.FilmUi

sealed interface FilmDetailsScreenState {

    data object Loading: FilmDetailsScreenState

    data class Content(
        val filmDetails: FilmDetailsUi
    ): FilmDetailsScreenState

    data object Error: FilmDetailsScreenState
}