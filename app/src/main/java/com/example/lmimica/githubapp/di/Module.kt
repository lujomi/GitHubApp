package com.example.lmimica.githubapp.di

import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.api.GithubApi
import com.example.lmimica.githubapp.api.GithubRepository
import com.example.lmimica.githubapp.presentation.homeactivity.HomeContract
import com.example.lmimica.githubapp.presentation.homeactivity.HomeActivityPresenter
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsContract
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsPresenter
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsContract
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsPresenter
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create() }

    fun provideInterceptor() :HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        return httpBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideGithubApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    fun provideGithubRepository(githubApi: GithubApi): GithubRepository {
        return GithubRepository(githubApi)
    }

    single { provideGson() }
    single { provideInterceptor() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideGithubApi(get()) }
    single { provideGithubRepository(get()) }
}
val presenterModule = module {
    factory { (view: HomeContract.View)->HomeActivityPresenter(view, get()) }
    factory { (view: RepositoryDetailsContract.View)->RepositoryDetailsPresenter(view) }
    factory { (view: UserDetailsContract.View)->UserDetailsPresenter(view) }
}