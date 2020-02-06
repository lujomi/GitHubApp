package com.example.lmimica.githubapp.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val userImage: String?,
    @SerializedName("html_url") val userUrl: String?,
    @SerializedName("id") val userId: String?,
    @SerializedName("node_id") val userNodeId: String?,
    @SerializedName("type") val userType: String?
)