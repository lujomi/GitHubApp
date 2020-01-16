package com.example.lmimica.githubapp.homescreen

import com.example.lmimica.githubapp.model.Repository

interface HomeContract {
    interface View {
        fun showList(repositories: List<Repository>)
        fun showUsersDetailsScreen(repository: Repository)
        fun showRepositoryDetailsScreen(repository: Repository)
        fun setSortButtonsVisibility()
        fun hideKeyboard()
    }

    interface Presenter {
        fun attach(view: View)
        fun sendRequest(query: String, sort: String)
        fun detach()
        fun onUserDetailsClicked(repository: Repository)
        fun onRepositoryDetailsClicked(repository: Repository)
        fun setKeyboard()
    }
}