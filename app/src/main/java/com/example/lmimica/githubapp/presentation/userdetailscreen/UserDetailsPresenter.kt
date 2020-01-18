package com.example.lmimica.githubapp.presentation.userdetailscreen

import com.example.lmimica.githubapp.model.UserInfo

class UserDetailsPresenter(private var view: UserDetailsContract.View?): UserDetailsContract.Presenter {
    private lateinit var userInfo: UserInfo

    override fun detach() {
       view = null
    }

    override fun setUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
        view?.setUserName(userInfo.userName)
        view?.setUserId(userInfo.userId)
        view?.setUserImage(userInfo.userImage)
        view?.setUserType(userInfo.userType)
        view?.setUserNodeId(userInfo.nodeId)
        if(userInfo.userUrl != null) view?.showWebBtn()
    }

    override fun openUserInWeb() {
        userInfo.userUrl?.let { view?.openInWeb(it) }
    }
}