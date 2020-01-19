package com.example.lmimica.githubapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(
    @SerializedName("login") val userName: String?,
    @SerializedName("avatar_url") val userImage: String?,
    @SerializedName("html_url") val userUrl: String?,
    @SerializedName("id") val userId: String?,
    @SerializedName("node_id") val userNodeId: String?,
    @SerializedName("type") val userType: String?
) : Parcelable