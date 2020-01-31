package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.UserInfo
import java.util.*

interface RepositoryDetailsContract {

    interface View {
        fun sendUserDetails(user: UserInfo)
        fun openInWeb(string: String)
        fun setRepositoryFields(repository: Repository)
    }

    interface Presenter {
        fun detach()
        fun showUserScreen()
        fun openRepoInWeb()
        fun setRepository(repository: Repository)
        fun formatDate(date: Date?): String
    }
}