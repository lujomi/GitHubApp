package com.example.lmimica.githubapp.di

import com.example.lmimica.githubapp.presentation.homeactivity.HomeContract
import com.example.lmimica.githubapp.presentation.homeactivity.HomeActivityPresenter
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsContract
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsPresenter
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsContract
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsPresenter
import org.koin.dsl.module

val presenterModule = module {
    factory { (view: HomeContract.View)->HomeActivityPresenter(view) }
    factory { (view: RepositoryDetailsContract.View)->RepositoryDetailsPresenter(view) }
    factory { (view: UserDetailsContract.View)->UserDetailsPresenter(view) }
}