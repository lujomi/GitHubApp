package com.example.lmimica.githubapp.homescreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.api.ApiClient
import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.model.RepositoriesResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenPresenter : HomeContract.Presenter {

    private var view: HomeContract.View? = null
    private var api: GithubApi

    init {
        api = ApiClient.getClient
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun sendRequest(query: String) {
        api.getRepository(query).enqueue(object : Callback<RepositoriesResponse> {
            override fun onFailure(call: Call<RepositoriesResponse>, t: Throwable) {
                view?.showLogMessage("Error in onFailure $t")
            }

            override fun onResponse(call: Call<RepositoriesResponse>, response: Response<RepositoriesResponse>) {
                view?.showLogMessage("onResponse is cal")
                view?.showList(response.body()!!.repositoriesList)
            }
        })
    }

    override fun detach() {
        view = null
    }

    override fun onUserDetailsClicked(repository: Repository) {
        view?.showUsersDetailsScreen(repository)
    }

    override fun onRepositoryDetailsClicked(repository: Repository) {
        view?.showRepositoryDetailsScreen(repository)
    }
}