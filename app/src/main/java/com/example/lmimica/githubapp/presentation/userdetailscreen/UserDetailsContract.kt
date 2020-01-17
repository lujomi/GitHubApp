package com.example.lmimica.githubapp.presentation.userdetailscreen

import com.example.lmimica.githubapp.model.UserInfo

interface UserDetailsContract {
    interface View {
        fun setUserImage(string: String?)
        fun setUserName(string: String?)
        fun setUserId(string: String?)
        fun openInWeb(string: String)
        fun showWebBtn()
    }
    interface Presenter {
        fun detach()
        fun setUserInfo(userInfo: UserInfo)
        fun openUserInWeb()
    }
}