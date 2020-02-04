package com.example.lmimica.githubapp.presentation.homeactivity

import com.example.lmimica.githubapp.presentation.RepositoryViewModel

interface HomeContract {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun showList(repositories: List<RepositoryViewModel>)
        fun showUsersDetailsScreen(repository: RepositoryViewModel)
        fun showRepositoryDetailsScreen(repository: RepositoryViewModel)
        fun setSortButtonsVisibility()
        fun hideKeyboard()
        fun showToastMessage(message: String)
    }

    interface Presenter {
        fun sendRequest(query: String, sort: String)
        fun detach()
        fun onUserDetailsClicked(repository: RepositoryViewModel)
        fun onRepositoryDetailsClicked(repository: RepositoryViewModel)
    }
}