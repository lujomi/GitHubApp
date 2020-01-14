package com.example.lmimica.githubapp.HomeScreen

import com.example.lmimica.githubapp.Model.User

interface HomeContract {
    interface View {
        fun showList(users: List<User>)
        fun showLogMessage(message: String)
        fun showUsersDetailsScreen(user: User)
        fun showRepositoryDetailsScreen(user: User)
    }

    interface Presenter {
        fun attach(view: View)
        fun sendRequest(query: String)
        fun detach()
        fun onUserDetailsClicked(user: User)
        fun onRepositoryDetailsClicked(user: User)
    }
}