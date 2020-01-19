package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import java.text.SimpleDateFormat
import java.util.*

class RepositoryDetailsPresenter(private var view: RepositoryDetailsContract.View?) :
    RepositoryDetailsContract.Presenter {
    private lateinit var repository: Repository


    private fun formatDate(date: Date?): String {
        if (date != null) {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            return simpleDateFormat.format(date)
        } else return ""
    }

    override fun detach() {
        view = null
    }

    override fun showUserScreen() {
        repository.userInfo?.let { view?.sendUserDetails(it) }
    }

    override fun openRepoInWeb() {
        repository.repositoryUrl?.let { view?.openInWeb(it) }
    }

    override fun setRepository(repository: Repository) {
        this.repository = repository
        view?.setRepositoryName(repository.repositoryName)
        view?.setProgramLanguageName(repository.programmingLanguage)
        view?.setRepositoryCreatedDate(formatDate(repository.createdDate))
        view?.setRepositoryUpdatedDate(formatDate(repository.updateDate))
        view?.setRepositoryDescription(repository.repositoryDescription)
        if (repository.repositoryUrl != null) view?.showWebBtn()
    }
}