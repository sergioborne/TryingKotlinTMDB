package com.sergioborne.tmdbkotlinchallenge.data.remote

import com.sergioborne.tmdbkotlinchallenge.data.Page
import retrofit2.Call
import retrofit2.http.GET

interface TmdbAPI {

  @GET("discover/movie") fun getMovies(): Call<Page>
}