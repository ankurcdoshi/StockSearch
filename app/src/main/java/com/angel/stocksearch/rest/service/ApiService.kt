package com.angel.stocksearch.rest.service

import com.angel.stocksearch.rest.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/bins/6c3je")
    fun getUsers(): Call<Users>
}