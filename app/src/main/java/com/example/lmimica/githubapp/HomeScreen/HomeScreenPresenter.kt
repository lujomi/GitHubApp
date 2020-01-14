package com.example.lmimica.githubapp.HomeScreen

import com.example.lmimica.githubapp.Model.User
import com.example.lmimica.githubapp.Api.ApiClient
import com.example.lmimica.githubapp.Api.ApiRequest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenPresenter : HomeContract.Presenter {

    private var view: HomeContract.View? = null
    private var api: ApiRequest

    init {
        api = ApiClient.getClient
    }

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun sendRequest(query: String) {
        api.getUsers(query).enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                view?.showLogMessage("Error in onFailure")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                view?.showLogMessage("onResponse is cal")
                view?.showList(response.body()!!)
            }
        })
    }

    override fun detach() {
        view = null
    }

    override fun onUserDetailsClicked(user: User) {
        view?.showUsersDetailsScreen(user)
    }

    override fun onRepositoryDetailsClicked(user: User) {
        view?.showRepositoryDetailsScreen(user)
    }
}