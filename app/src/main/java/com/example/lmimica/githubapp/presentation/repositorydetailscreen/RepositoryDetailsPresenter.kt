package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.presentation.RepositoryViewModel

class RepositoryDetailsPresenter(private var view: RepositoryDetailsContract.View?) :
    RepositoryDetailsContract.Presenter {
    private lateinit var repository: RepositoryViewModel


    override fun detach() {
        view = null
    }

    override fun showUserScreen() {
        repository.userInfo?.let { view?.sendUserDetails(it) }
    }

    override fun openRepoInWeb() {
        repository.repositoryUrl?.let { view?.openInWeb(it) }
    }

    override fun setRepository(repository: RepositoryViewModel) {
        this.repository = repository
        view?.setRepositoryFields(repository)
    }
}