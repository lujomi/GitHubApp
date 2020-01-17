package com.example.lmimica.githubapp.homeactivity

import com.example.lmimica.githubapp.model.Repository

interface HomeContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showList(repositories: List<Repository>)
        fun showUsersDetailsScreen(repository: Repository)
        fun showRepositoryDetailsScreen(repository: Repository)
        fun setSortButtonsVisibility()
        fun hideKeyboard()
    }

    interface Presenter {
        fun sendRequest(query: String, sort: String)
        fun detach()
        fun onUserDetailsClicked(repository: Repository)
        fun onRepositoryDetailsClicked(repository: Repository)
        fun setKeyboard()
    }
}