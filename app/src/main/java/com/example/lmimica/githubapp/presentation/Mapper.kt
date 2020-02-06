package com.example.lmimica.githubapp.presentation

import android.content.Context
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.model.RepositoriesResponse
import com.example.lmimica.githubapp.model.UserInfo
import java.text.SimpleDateFormat
import java.util.*

class Mapper(private val context: Context) {
    fun mapRepository(repository: RepositoriesResponse): List<RepositoryViewModel> {
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
                userInfo = it.userInfo?.mapUserInfo(),
                repositoryDescription = it.repositoryDescription
                    ?: context.getString(R.string.info_not_available),
                createdDate = formatDate(it.createdDate),
                updateDate = formatDate(it.updateDate)
            )
        }
    }

    private fun UserInfo.mapUserInfo() = UserInfoViewModel(
        userName = userName ?: context.getString(R.string.info_not_available),
        userImage = userImage,
        userUrl = userUrl,
        userNodeId = userNodeId ?: context.getString(R.string.info_not_available),
        userType = userType ?: context.getString(R.string.info_not_available),
        userId = userId ?: context.getString(R.string.info_not_available)
    )

    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val simpleDateFormat = SimpleDateFormat.getDateInstance()
            simpleDateFormat.format(date)
        } else context.getString(R.string.unknown_date)
    }
}