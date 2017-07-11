package com.sergioborne.tmdbkotlinchallenge.di

import android.app.Application
import android.content.Context
import com.sergioborne.tmdbkotlinchallenge.data.MovieRepository
import com.sergioborne.tmdbkotlinchallenge.movieList.MovieViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class, ApiModule::class)
)
interface AppComponent {
  fun inject(viewModelModule: MovieViewModel)
  fun inject(activity: Context)

  fun provideMovieRepository(): MovieRepository

  companion object Factory {
    fun create(app: Application, baseUrl: String): AppComponent {
      val appComponent = DaggerAppComponent.builder().
          appModule(AppModule(app)).
          apiModule(ApiModule()).
          netModule(NetModule(baseUrl)).
          repositoryModule(RepositoryModule()).
          build()
      return appComponent
    }
  }

}


