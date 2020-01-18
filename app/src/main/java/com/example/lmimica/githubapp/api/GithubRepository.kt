package com.example.lmimica.githubapp.api

import com.example.lmimica.githubapp.model.RepositoriesResponse
import retrofit2.Call

class GithubRepository(
    private val githubApi: GithubApi) {
    fun getRepository(action: String, sort: String): Call<RepositoriesResponse>{
        return githubApi.getRepository(action, sort)
    }
}