package com.sergioborne.tmdbkotlinchallenge.di

import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module class NetModule(val baseUrl: String) {

  val QUERY_PARAMETER = "api_key"
  private val API_KEY = "22f81827447bff40fff2f86f8f355f08"

  @Provides
  @Singleton
  fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    return gsonBuilder.create()
  }

  @Provides
  @Singleton
  fun provideOkhttpClient(interceptor: Interceptor): OkHttpClient {
    val client = OkHttpClient.Builder().addInterceptor(interceptor)
    return client.build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
  }

  @Provides
  @Singleton
  fun provideInterceptor(): Interceptor {
    return Interceptor { chain ->
      //copy and alter url
      val url = chain
          .request()
          .url()
          .newBuilder()
          .addQueryParameter(QUERY_PARAMETER, API_KEY)
          .build()

      //copy and alter request
      val request = chain.request()
          .newBuilder()
          .url(url)
          .build()

      Log.d("NETWORK","Getting $url")

      chain.proceed(request)
    }
  }
}


