package com.example.lmimica.githubapp.di

import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.presentation.Mapper
import com.example.lmimica.githubapp.presentation.homeactivity.HomeContract
import com.example.lmimica.githubapp.presentation.homeactivity.HomeActivityPresenter
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsContract
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsPresenter
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsContract
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsPresenter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    fun provideInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        return httpBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideGithubApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    single { provideGson() }
    single { provideInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideGithubApi(get()) }
}

val mapperModule = module {
    single { Mapper(androidContext()) }
}

val presenterModule = module {
    factory { (view: HomeContract.View) -> HomeActivityPresenter(view, get(), get()) }
    factory { (view: RepositoryDetailsContract.View) -> RepositoryDetailsPresenter(view) }
    factory { (view: UserDetailsContract.View) -> UserDetailsPresenter(view) }
}

