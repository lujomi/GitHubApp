package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.presentation.RepositoryViewModel
import com.example.lmimica.githubapp.model.UserInfo

interface RepositoryDetailsContract {

    interface View {
        fun sendUserDetails(user: UserInfo)
        fun openInWeb(string: String)
        fun setRepositoryFields(repository: RepositoryViewModel)
    }

    interface Presenter {
        fun detach()
        fun showUserScreen()
        fun openRepoInWeb()
        fun setRepository(repository: RepositoryViewModel)
    }
}