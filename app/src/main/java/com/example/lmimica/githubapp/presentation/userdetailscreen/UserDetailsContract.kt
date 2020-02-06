package com.example.lmimica.githubapp.presentation.userdetailscreen

import com.example.lmimica.githubapp.presentation.UserInfoViewModel

interface UserDetailsContract {
    interface View {
        fun setUserFields(userInfo: UserInfoViewModel)
        fun openInWeb(string: String)
    }

    interface Presenter {
        fun detach()
        fun setUserInfo(userInfo: UserInfoViewModel)
        fun openUserInWeb()
    }
}