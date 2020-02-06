package com.example.lmimica.githubapp.presentation.userdetailscreen

import com.example.lmimica.githubapp.presentation.UserInfoViewModel

class UserDetailsPresenter(private var view: UserDetailsContract.View?) :
    UserDetailsContract.Presenter {
    private lateinit var userInfo: UserInfoViewModel

    override fun detach() {
        view = null
    }

    override fun setUserInfo(userInfo: UserInfoViewModel) {
        this.userInfo = userInfo
        view?.setUserFields(userInfo)
    }

    override fun openUserInWeb() {
        userInfo.userUrl?.let { view?.openInWeb(it) }
    }
}