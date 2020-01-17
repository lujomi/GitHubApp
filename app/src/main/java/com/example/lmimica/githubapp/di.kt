package com.example.lmimica.githubapp

import com.example.lmimica.githubapp.homescreen.HomeContract
import com.example.lmimica.githubapp.homescreen.HomeScreenPresenter
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsContract
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsPresenter
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsContract
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { (view: HomeContract.View)->HomeScreenPresenter(view) }
    factory { (view: RepositoryDetailsContract.View)->RepositoryDetailsPresenter(view) }
    factory { (view: UserDetailsContract.View)->UserDetailsPresenter(view) }
}