package com.example.lmimica.githubapp.homescreen

import com.example.lmimica.githubapp.model.Repository

interface HomeContract {
    interface View {
        fun showList(repositories: List<Repository>)
        fun showLogMessage(message: String)
        fun showUsersDetailsScreen(repository: Repository)
        fun showRepositoryDetailsScreen(repository: Repository)
    }

    interface Presenter {
        fun attach(view: View)
        fun sendRequest(query: String)
        fun detach()
        fun onUserDetailsClicked(repository: Repository)
        fun onRepositoryDetailsClicked(repository: Repository)
    }
}