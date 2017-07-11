package com.sergioborne.tmdbkotlinchallenge.di

import com.sergioborne.tmdbkotlinchallenge.data.remote.TmdbAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
  @Provides
  @Singleton
  fun providesImdbAPI(retrofit: Retrofit): TmdbAPI {
    return retrofit.create<TmdbAPI>(TmdbAPI::class.java)
  }
}