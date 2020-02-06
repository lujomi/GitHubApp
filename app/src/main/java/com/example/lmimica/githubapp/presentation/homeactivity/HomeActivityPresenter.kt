package com.example.lmimica.githubapp.presentation.homeactivity

import com.example.lmimica.githubapp.presentation.Mapper
import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.model.RepositoriesResponse
import com.example.lmimica.githubapp.presentation.RepositoryViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeActivityPresenter(
    private var view: HomeContract.View?,
    private val api: GithubApi,
    private val mapper: Mapper
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
                if (response.isSuccessful && response.body() != null
                    && response.body()?.repositoriesList?.isNotEmpty()!!
                ) {
                    val repositoryList: List<RepositoryViewModel>? =
                        mapper.mapRepository(response.body()!!)
                    view?.showList(repositoryList!!)
                } else {
                    view?.showToastMessage("List is not available")
                }
                view?.setSortButtonsVisibility()
            }
        })
    }

    override fun detach() {
        view = null
    }

    override fun onUserDetailsClicked(repository: RepositoryViewModel) {
        view?.showUsersDetailsScreen(repository)
    }

    override fun onRepositoryDetailsClicked(repository: RepositoryViewModel) {
        view?.showRepositoryDetailsScreen(repository)
    }
}