package com.example.lmimica.githubapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Repository(@SerializedName("name") val repositoryName: String?,
                      @SerializedName("watchers") val followersNumber: String?,
                      @SerializedName("forks") val forksNumber: String?,
                      @SerializedName("open_issues") val issuesNumber: String?,
                      @SerializedName("language") val programmingLanguage: String?,
                      @SerializedName("html_url") val repositoryUrl: String?,
                      @SerializedName("owner") val userInfo: UserInfo?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(UserInfo::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(repositoryName)
        parcel.writeString(followersNumber)
        parcel.writeString(forksNumber)
        parcel.writeString(issuesNumber)
        parcel.writeString(programmingLanguage)
        parcel.writeString(repositoryUrl)
        parcel.writeParcelable(userInfo, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }
}