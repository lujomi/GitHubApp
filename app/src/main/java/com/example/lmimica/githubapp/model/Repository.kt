package com.example.lmimica.githubapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Repository(
    @SerializedName("name") val repositoryName: String?,
    @SerializedName("watchers") val followersNumber: String?,
    @SerializedName("forks") val forksNumber: String?,
    @SerializedName("open_issues") val issuesNumber: String?,
    @SerializedName("language") val programmingLanguage: String?,
    @SerializedName("html_url") val repositoryUrl: String?,
    @SerializedName("owner") val userInfo: UserInfo?,
    @SerializedName("description") val repositoryDescription: String?,
    @SerializedName("created_at") val createdDate: Date?,
    @SerializedName("updated_at") val updateDate: Date?
) : Parcelable
