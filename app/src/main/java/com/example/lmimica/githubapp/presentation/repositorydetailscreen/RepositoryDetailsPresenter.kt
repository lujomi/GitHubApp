package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import com.example.lmimica.githubapp.model.Repository
import java.text.SimpleDateFormat
import java.util.*

class RepositoryDetailsPresenter(private var view: RepositoryDetailsContract.View?): RepositoryDetailsContract.Presenter {
    private lateinit var repository: Repository


    override fun formatDate(date: Date?, string: String): String {
        if (date != null){
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            return simpleDateFormat.format(date)
        } else return string
    }

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
        view!!.setRepositoryCreatedDate(repository.createdDate)
        view!!.setRepositoryUpdatedDate(repository.updateDate)
        if(repository.repositoryUrl != null) view!!.showWebBtn()
    }
}