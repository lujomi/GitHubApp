package com.example.lmimica.githubapp.Api

import com.example.lmimica.githubapp.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    @GET("/users?")
    fun getUsers(@Query("since") action: String): Call<List<User>>
}