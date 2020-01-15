package com.example.lmimica.githubapp.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.UserInfo

interface RepositoryDetailsContract {

    interface View {
        fun setRepositoryName(string: String)
        fun setProgramLnaguageName(string: String)
        fun sendUserDetails(user: UserInfo)
        fun openInWeb(string: String)
    }

    interface Presenter{
        fun attach(view: View)
        fun detach()
        fun repositoryReceived()
        fun showUserScreen()
        fun openRepoInWeb()
        fun setRepository(repository: Repository)
    }
}