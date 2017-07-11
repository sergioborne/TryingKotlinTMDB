package com.sergioborne.tmdbkotlinchallenge

import android.app.Application
import com.sergioborne.tmdbkotlinchallenge.di.AppComponent

class App : Application() {

  val BASE_URL = "https://api.themoviedb.org/3/"

  companion object {
    @JvmStatic lateinit var appComponent: AppComponent
  }

  override fun onCreate() {
    super.onCreate()
    appComponent = AppComponent.create(this, BASE_URL)
  }
}


