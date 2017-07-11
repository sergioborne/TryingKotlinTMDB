package com.sergioborne.tmdbkotlinchallenge.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sergioborne.tmdbkotlinchallenge.Resource
import com.sergioborne.tmdbkotlinchallenge.data.remote.TmdbAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(val api: TmdbAPI) {

  fun getMovies(): LiveData<Resource<List<Movie>>> {
    val data = MutableLiveData<Resource<List<Movie>>>()
    api.getMovies().enqueue(object : Callback<Page> {
      override fun onResponse(call: Call<Page>?, response: Response<Page>?) {
        data.value = Resource.success(response?.body()?.results)
      }

      override fun onFailure(call: Call<Page>?, t: Throwable?) {
        val exception = AppException(t)
        data.value = Resource.error(exception)
      }
    })
    return data
  }
}




