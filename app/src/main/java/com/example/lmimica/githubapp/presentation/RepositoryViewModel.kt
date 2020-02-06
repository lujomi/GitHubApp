package com.example.lmimica.githubapp.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryViewModel(
    val repositoryName: String?,
    val followersNumber: String?,
    val forksNumber: String?,
    val issuesNumber: String?,
    val programmingLanguage: String?,
    val repositoryUrl: String?,
    val userInfo: UserInfoViewModel?,
    val repositoryDescription: String?,
    val createdDate: String?,
    val updateDate: String?
) : Parcelable