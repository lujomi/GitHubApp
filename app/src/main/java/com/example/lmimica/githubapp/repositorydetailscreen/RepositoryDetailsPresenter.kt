package com.example.lmimica.githubapp.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository

class RepositoryDetailsPresenter: RepositoryDetailsContract.Presenter {
    private lateinit var repository: Repository
    private var view: RepositoryDetailsContract.View? = null

    override fun attach(view: RepositoryDetailsContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun repositoryReceived() {
        repository.repositoryName?.let { view!!.setRepositoryName(it) }
        repository.programmingLanguage?.let { view!!.setProgramLnaguageName(it) }
    }

    override fun showUserScreen() {
        repository.userInfo?.let { view!!.sendUserDetails(it) }
    }

    override fun openRepoInWeb() {
        repository.repositoryUrl?.let { view!!.openInWeb(it) }
    }

    override fun setRepository(repository: Repository) {
        this.repository = repository
    }
}