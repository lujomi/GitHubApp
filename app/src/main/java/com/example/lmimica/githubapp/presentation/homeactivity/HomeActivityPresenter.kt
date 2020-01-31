package com.example.lmimica.githubapp.presentation.homeactivity

import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.RepositoriesResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeActivityPresenter(
    private var view: HomeContract.View?,
    private val api: GithubApi
) : HomeContract.Presenter {

    override fun sendRequest(query: String, sort: String) {
        view?.showProgress()
        view?.hideKeyboard()
        api.getRepository(query, sort).enqueue(object : Callback<RepositoriesResponse> {
            override fun onFailure(call: Call<RepositoriesResponse>, t: Throwable) {
                view?.hideProgress()
                Timber.d("onFailure is call $t")
            }

            override fun onResponse(
                call: Call<RepositoriesResponse>,
                response: Response<RepositoriesResponse>
            ) {
                Timber.d("onResponse is call")
                view?.hideProgress()
                if (response.isSuccessful) {
                    if (response.body()?.repositoriesList?.isEmpty()!!) {
                        view?.showToastMessage("List is empty")
                    } else {
                        response.body()?.repositoriesList?.let { view?.showList(it) }
                    }
                } else {
                    response.errorBody()?.string()?.let { view?.showToastMessage(it) }
                }
                view?.setSortButtonsVisibility()
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