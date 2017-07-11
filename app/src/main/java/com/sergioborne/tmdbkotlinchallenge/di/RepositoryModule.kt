package com.sergioborne.tmdbkotlinchallenge.di

import com.sergioborne.tmdbkotlinchallenge.data.MovieRepository
import com.sergioborne.tmdbkotlinchallenge.data.remote.TmdbAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

  @Provides @Singleton fun provideMovieRepository(api: TmdbAPI): MovieRepository {
    return MovieRepository(api)
  }
}


