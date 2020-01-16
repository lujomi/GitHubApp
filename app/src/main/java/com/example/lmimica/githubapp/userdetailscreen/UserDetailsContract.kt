package com.example.lmimica.githubapp.userdetailscreen

import com.example.lmimica.githubapp.model.UserInfo

interface UserDetailsContract {
    interface View {
        fun setUserImage(string: String?)
        fun setUserName(string: String?)
        fun setUserId(string: String?)
        fun openInWeb(string: String)
    }
    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun setUserInfo(userInfo: UserInfo)
        fun openUserInWeb()
    }
}