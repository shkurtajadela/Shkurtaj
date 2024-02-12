package com.adelashkurtaj.shkurtaj.di


import com.google.gson.Gson
import com.adelashkurtaj.shkurtaj.data.FilmMapper
import com.adelashkurtaj.shkurtaj.data.local.AppDatabase
import com.adelashkurtaj.shkurtaj.data.local.CacheDao
import com.adelashkurtaj.shkurtaj.data.local.FavoriteFilmDao
import com.adelashkurtaj.shkurtaj.data.remote.ApiService
import com.adelashkurtaj.shkurtaj.data.repository.FavoriteFilmsRepositoryImpl
import com.adelashkurtaj.shkurtaj.data.repository.FilmsRepositoryImpl
import com.adelashkurtaj.shkurtaj.domain.FavoriteFilmsRepository
import com.adelashkurtaj.shkurtaj.domain.FilmsRepository
import com.adelashkurtaj.shkurtaj.domain.GetPopularFilmsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PopularFilmsModule {



    @Provides
    @ViewModelScoped
    fun provideGetPopularFilmsUseCase(
        filmsRepository: FilmsRepository,
        favoriteFilmsRepository: FavoriteFilmsRepository
    ): GetPopularFilmsUseCase {
        return GetPopularFilmsUseCase(
            filmsRepository = filmsRepository,
            favoriteFilmsRepository = favoriteFilmsRepository
        )
    }

    @Provides
    @ViewModelScoped
    fun provideFilmsRepository(
        cacheDao: CacheDao,
        mapper: FilmMapper,
        apiService: ApiService
    ): FilmsRepository {
        return FilmsRepositoryImpl(
            apiService = apiService,
            cacheDao = cacheDao,
            mapper = mapper,
            gson = Gson()
        )
    }

}