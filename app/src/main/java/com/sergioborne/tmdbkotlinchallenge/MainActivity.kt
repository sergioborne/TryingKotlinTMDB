package com.sergioborne.tmdbkotlinchallenge

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.sergioborne.tmdbkotlinchallenge.movieList.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity() {

  var movieViewModel: MovieViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    movieViewModel = MovieViewModel.create(this)
    App.appComponent.inject(movieViewModel!!)

    movieViewModel?.loadMovies()?.observe(this, Observer { resource ->
      when (resource?.status) {
        Resource.Status.SUCCESS -> {
          val movies = resource.data
          text.text = movies.toString()
        }
        Resource.Status.ERROR -> {
          Toast.makeText(this, "Error: " + resource.exception?.message, Toast.LENGTH_LONG).show()
        }
        Resource.Status.LOADING ->{}
      }
    })
  }
}
