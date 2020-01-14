package com.example.lmimica.githubapp.api

import com.example.lmimica.githubapp.model.RepositoriesResponse
import com.example.lmimica.githubapp.model.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getUsers(@Query("q") action: String): Call<RepositoriesResponse>
}