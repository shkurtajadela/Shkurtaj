package com.adelashkurtaj.shkurtaj.data.remote

import com.adelashkurtaj.shkurtaj.data.remote.models.FilmDetailsResponse
import com.adelashkurtaj.shkurtaj.data.remote.models.FilmsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Интерфейс для получения данных из сети
 *
 * @author Shkurtaj Adela
 */

interface ApiService {

    @GET("$FILMS_PATH/top")
    @Headers("x-api-key: $API_KEY")
    suspend fun getTopFilms(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS"
    ): FilmsResponse

    @GET("$FILMS_PATH/{id}")
    @Headers("x-api-key: $API_KEY")
    suspend fun getFilmDetails(@Path("id") id: Int): FilmDetailsResponse

    @GET(SEARCH_FILMS_PATH)
    @Headers("x-api-key: $API_KEY")
    suspend fun searchFilm(
        @Query("keyword") keyword: String
    ): FilmsResponse

    companion object {
        const val BASE_URL = "https://kinopoiskapiunofficial.tech"
        const val FILMS_PATH = "/api/v2.2/films"
        const val TOP_100_POPULAR_FILMS_REQUEST = "$BASE_URL$FILMS_PATH/top?type=TOP_100_POPULAR_FILMS"
        const val SEARCH_FILMS_PATH = "/api/v2.1/films/search-by-keyword"
        const val API_KEY = "fe64683c-54c6-4a94-aee3-da4ef63e8d09"
    }
}