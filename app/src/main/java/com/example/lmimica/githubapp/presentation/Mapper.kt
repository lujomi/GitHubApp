package com.example.lmimica.githubapp.presentation

import android.content.Context
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.model.RepositoriesResponse
import java.text.SimpleDateFormat
import java.util.*

class Mapper(private val context: Context) {
    fun map(repository: RepositoriesResponse): List<RepositoryViewModel> {
        return repository.repositoriesList.map {
            RepositoryViewModel(
                repositoryName = it.repositoryName
                    ?: context.getString(R.string.info_not_available),
                followersNumber = it.followersNumber
                    ?: context.getString(R.string.info_not_available),
                forksNumber = it.forksNumber ?: context.getString(R.string.info_not_available),
                issuesNumber = it.issuesNumber ?: context.getString(R.string.info_not_available),
                programmingLanguage = it.programmingLanguage
                    ?: context.getString(R.string.info_not_available),
                repositoryUrl = it.repositoryUrl,
                userInfo = it.userInfo,
                repositoryDescription = it.repositoryDescription
                    ?: context.getString(R.string.info_not_available),
                createdDate = formatDate(it.createdDate),
                updateDate = formatDate(it.updateDate)
            )
        }
    }

    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            simpleDateFormat.format(date)
        } else context.getString(R.string.unknown_date)
    }
}