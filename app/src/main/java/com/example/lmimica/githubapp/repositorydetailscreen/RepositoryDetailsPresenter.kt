package com.example.lmimica.githubapp.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository

class RepositoryDetailsPresenter(private var view: RepositoryDetailsContract.View?): RepositoryDetailsContract.Presenter {
    private lateinit var repository: Repository

    override fun detach() {
        view = null
    }

    override fun showUserScreen() {
        repository.userInfo?.let { view!!.sendUserDetails(it) }
    }

    override fun openRepoInWeb() {
        repository.repositoryUrl?.let { view!!.openInWeb(it) }
    }

    override fun setRepository(repository: Repository) {
        this.repository = repository
        view!!.setRepositoryName(repository.repositoryName)
        view!!.setProgramLnaguageName(repository.programmingLanguage)
        if(repository.repositoryUrl != null) view!!.showWebBtn()
    }
}