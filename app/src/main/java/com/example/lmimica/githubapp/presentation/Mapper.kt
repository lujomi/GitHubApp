package com.example.lmimica.githubapp.presentation

import com.example.lmimica.githubapp.model.RepositoriesResponse
import java.text.SimpleDateFormat
import java.util.*

object Mapper {
    fun map(repository: RepositoriesResponse): List<RepositoryViewModel> {
        return repository.repositoriesList.map {
            RepositoryViewModel(
                repositoryName = it.repositoryName,
                followersNumber = it.followersNumber,
                forksNumber = it.forksNumber,
                issuesNumber = it.issuesNumber,
                programmingLanguage = it.programmingLanguage,
                repositoryUrl = it.repositoryUrl,
                userInfo = it.userInfo,
                repositoryDescription = it.repositoryDescription,
                createdDate = formatDate(it.createdDate),
                updateDate = formatDate(it.updateDate)
            )
        }
    }

    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            simpleDateFormat.format(date)
        } else ""
    }
}