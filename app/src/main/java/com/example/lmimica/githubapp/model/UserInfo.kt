package com.example.lmimica.githubapp.model

import com.google.gson.annotations.SerializedName

data class UserInfo(@SerializedName("login") val userName: String,
                    @SerializedName("avatar_url") val userImage: String)