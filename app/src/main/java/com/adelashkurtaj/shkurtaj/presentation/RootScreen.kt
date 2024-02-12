package com.adelashkurtaj.shkurtaj.presentation

import androidx.compose.runtime.Composable
import com.adelashkurtaj.shkurtaj.navigation.AppNavGraph
import com.adelashkurtaj.shkurtaj.navigation.Screen
import com.adelashkurtaj.shkurtaj.navigation.rememberNavigationState
import com.adelashkurtaj.shkurtaj.presentation.favorite_films.FavoriteFilmsScreen
import com.adelashkurtaj.shkurtaj.presentation.film_details.FilmDetailsScreen
import com.adelashkurtaj.shkurtaj.presentation.popular_films.PopularFilmsScreen
import com.adelashkurtaj.shkurtaj.presentation.search_films.SearchArea
import com.adelashkurtaj.shkurtaj.presentation.search_films.SearchFilmsScreen
import com.adelashkurtaj.shkurtaj.presentation.search_films.getSearchAreaFromInt

@Composable
fun RootScreen() {

    val navigationState = rememberNavigationState()

    AppNavGraph(
        navHostController = navigationState.navHostController,
        popularFilmsScreenContent = {
            PopularFilmsScreen(
                onNavigateToFilmDetails = { filmId ->
                    navigationState.navigateToFilmDetailsScreen(id = filmId)
                },
                onNavigateToFavoriteFilms = {
                    navigationState.navigateTo(Screen.ROUTE_FAVORITE_FILMS_SCREEN)
                },
                onNavigateToSearchFilms = { area ->
                    navigationState.navigateToSearchScreen(area = area.ordinal)
                }
            )
        },
        favoriteFilmsScreenContent = {
            FavoriteFilmsScreen(
                onNavigateToPopularFilms = {
                    navigationState.navigateTo(Screen.ROUTE_POPULAR_FILMS_SCREEN)
                },
                onNavigateToSearchFilms = { area ->
                    navigationState.navigateToSearchScreen(area = area.ordinal)
                },
                onNavigateToFilmDetails = { filmId ->
                    navigationState.navigateToFilmDetailsScreen(id = filmId)
                }
            )
        },
        filmDetailsScreenContent = { id ->
            FilmDetailsScreen(filmId = id) {
                navigationState.navigateBack()
            }
        },
        searchFilmsScreenContent = { area ->
            SearchFilmsScreen(
                area = getSearchAreaFromInt(area = area) ?: SearchArea.Populars,
                onBackPressed = { navigationState.navigateBack() },
                onNavigateToFilmDetails = { filmId ->
                    navigationState.navigateToFilmDetailsScreen(id = filmId)
                }
            )
        }
    )
}