package com.example.lmimica.githubapp

import com.example.lmimica.githubapp.homeactivity.HomeContract
import com.example.lmimica.githubapp.homeactivity.HomeActivityPresenter
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsContract
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsPresenter
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsContract
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { (view: HomeContract.View)->HomeActivityPresenter(view) }
    factory { (view: RepositoryDetailsContract.View)->RepositoryDetailsPresenter(view) }
    factory { (view: UserDetailsContract.View)->UserDetailsPresenter(view) }
}