package com.sergioborne.tmdbkotlinchallenge.movieList

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.sergioborne.tmdbkotlinchallenge.data.MovieRepository
import javax.inject.Inject

class MovieViewModel : ViewModel() {

  private lateinit var repository: MovieRepository

  @Inject fun init(repository: MovieRepository) {
    this.repository = repository
  }

  fun loadMovies() = repository.getMovies()

  companion object {
    fun create(activity: FragmentActivity): MovieViewModel {
      var movieDetailViewModel = ViewModelProviders.of(activity).get(MovieViewModel::class.java)
      return movieDetailViewModel
    }
  }
}


