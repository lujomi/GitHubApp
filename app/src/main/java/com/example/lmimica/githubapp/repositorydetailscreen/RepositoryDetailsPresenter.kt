package com.example.lmimica.githubapp.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.UserInfo

class RepositoryDetailsPresenter: RepositoryDetailsContract.Presenter {
    private var view: RepositoryDetailsContract.View? = null

    override fun attach(view: RepositoryDetailsContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun repositoryReceived(repository: Repository) {
        repository.repositoryName?.let { view!!.setRepositoryName(it) }
        repository.programmingLanguage?.let { view!!.setProgramLnaguageName(it) }
    }

    override fun showUserScreen(userInfo: UserInfo) {
        view!!.sendUserDetails(userInfo)
    }

    override fun openRepoInWeb(repositoryUrl: String) {
        view!!.openInWeb(repositoryUrl)
    }
}