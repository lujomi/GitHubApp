package com.example.lmimica.githubapp.api

import com.example.lmimica.githubapp.model.RepositoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun getRepository(@Query("q") action: String): Call<RepositoriesResponse>
    fun sortReository(@Query("sort") action: String): Call<RepositoriesResponse>
}