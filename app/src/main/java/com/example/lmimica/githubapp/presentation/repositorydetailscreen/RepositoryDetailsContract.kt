package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.UserInfo

interface RepositoryDetailsContract {

    interface View {
        fun setRepositoryDescription(string: String?)
        fun setRepositoryName(string: String?)
        fun setProgramLanguageName(string: String?)
        fun setRepositoryCreatedDate(string: String)
        fun setRepositoryUpdatedDate(string: String)
        fun sendUserDetails(user: UserInfo)
        fun openInWeb(string: String)
        fun showWebBtn()
    }

    interface Presenter {
        fun detach()
        fun showUserScreen()
        fun openRepoInWeb()
        fun setRepository(repository: Repository)
    }
}