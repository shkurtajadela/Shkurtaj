package com.adelashkurtaj.shkurtaj.di

import android.app.Application
import androidx.room.Room
import com.adelashkurtaj.shkurtaj.data.FilmMapper
import com.adelashkurtaj.shkurtaj.data.local.AppDatabase
import com.adelashkurtaj.shkurtaj.data.local.CacheDao
import com.adelashkurtaj.shkurtaj.data.local.FavoriteFilmDao
import com.adelashkurtaj.shkurtaj.data.remote.ApiService
import com.adelashkurtaj.shkurtaj.data.repository.FavoriteFilmsRepositoryImpl
import com.adelashkurtaj.shkurtaj.domain.FavoriteFilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesApiService(): ApiService {

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCacheDao(db: AppDatabase): CacheDao {
        return db.cacheDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteFilmDao(db: AppDatabase): FavoriteFilmDao {
        return db.favoriteFilmDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteFilmsRepository(
        mapper: FilmMapper,
        favoriteFilmDao: FavoriteFilmDao
    ): FavoriteFilmsRepository {
        return FavoriteFilmsRepositoryImpl(
            mapper = mapper,
            favoriteFilmDao = favoriteFilmDao
        )
    }

    @Provides
    @Singleton
    fun provideMapper(): FilmMapper {
        return FilmMapper()
    }

}
