package com.example.lmimica.githubapp.presentation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfoViewModel(
    val userName: String?,
    val userImage: String?,
    val userUrl: String?,
    val userId: String?,
    val userNodeId: String?,
    val userType: String?
) : Parcelable