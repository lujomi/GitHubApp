package com.example.lmimica.githubapp.model

import com.google.gson.annotations.SerializedName

data class Repository(@SerializedName("name") val repositoryName: String,
                      @SerializedName("watchers") val followersNumber: String,
                      @SerializedName("forks") val forksNumber: String,
                      @SerializedName("open_issues") val issuesNumber: String,
                      @SerializedName("owner") val userInfo: UserInfo)