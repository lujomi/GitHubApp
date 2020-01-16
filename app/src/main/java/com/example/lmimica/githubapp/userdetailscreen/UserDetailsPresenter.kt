package com.example.lmimica.githubapp.userdetailscreen

import com.example.lmimica.githubapp.model.UserInfo

class UserDetailsPresenter: UserDetailsContract.Presenter {
    private lateinit var userInfo: UserInfo
    private var view: UserDetailsContract.View? = null

    override fun attach(view: UserDetailsContract.View) {
        this.view = view
    }

    override fun detach() {
       view = null
    }

    override fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
        view!!.setUserName(userInfo.userName)
        view!!.setUserId(userInfo.userId)
        view!!.setUserImage(userInfo.userImage)
    }

    override fun openUserInWeb() {
        userInfo.userUrl?.let { view!!.openInWeb(it) }
    }
}