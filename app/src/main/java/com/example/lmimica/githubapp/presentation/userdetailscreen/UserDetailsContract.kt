package com.example.lmimica.githubapp.presentation.userdetailscreen

import com.example.lmimica.githubapp.model.UserInfo

interface UserDetailsContract {
    interface View {
        fun setUserFields(userInfo: UserInfo)
        fun openInWeb(string: String)
    }

    interface Presenter {
        fun detach()
        fun setUserInfo(userInfo: UserInfo)
        fun openUserInWeb()
    }
}