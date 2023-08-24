package dev.masian.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/posts")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun<T> buildClient(apiInterface :Class<T>):T{
        return retrofit.create(apiInterface)
    }
}