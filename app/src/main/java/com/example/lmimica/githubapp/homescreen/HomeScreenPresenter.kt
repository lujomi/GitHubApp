package com.example.lmimica.githubapp.homescreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.api.ApiClient
import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.model.RepositoriesResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeScreenPresenter(private var view: HomeContract.View?) : HomeContract.Presenter {

    private var api: GithubApi

    init {
        api = ApiClient.getClient
    }

    override fun sendRequest(query: String, sort: String) {
        api.getRepository(query, sort).enqueue(object : Callback<RepositoriesResponse> {
            override fun onFailure(call: Call<RepositoriesResponse>, t: Throwable) {
               Timber.d("onFailure is call $t")
            }

            override fun onResponse(call: Call<RepositoriesResponse>, response: Response<RepositoriesResponse>) {
                Timber.d("Response is call")
                view!!.showList(response.body()!!.repositoriesList)
                view!!.setSortButtonsVisibility()
            }
        })
    }

    override fun detach() {
        view = null
    }

    override fun onUserDetailsClicked(repository: Repository) {
        view!!.showUsersDetailsScreen(repository)
    }

    override fun onRepositoryDetailsClicked(repository: Repository) {
        view!!.showRepositoryDetailsScreen(repository)
    }

    override fun setKeyboard() {
        view!!.hideKeyboard()
    }
}