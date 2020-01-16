package com.example.lmimica.githubapp.repositorydetailscreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.model.UserInfo
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsScreen
import kotlinx.android.synthetic.main.activity_repository_details_screen.*

class RepositoryDetailsScreen : AppCompatActivity(), RepositoryDetailsContract.View {
    private val repoDetailsPresenter = RepositoryDetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details_screen)

        repoDetailsPresenter.attach(this)
        repoDetailsPresenter.setRepository(intent.extras?.getParcelable<Repository>(Constants.REPOSITIRY_KEY)!!)

        btnShowUser.setOnClickListener {
           repoDetailsPresenter.showUserScreen()
        }

        btnOpenWeb.setOnClickListener {
           repoDetailsPresenter.openRepoInWeb() }
        }

    override fun setRepositoryName(string: String?) {
        repositoryNameScreen.text = resources.getString(R.string.repo_name, string ?: resources.getString(R.string.name_not_available))
    }

    override fun setProgramLnaguageName(string: String?) {
        programmingLanguageName.text = resources.getString(R.string.programming_language, string ?: resources.getString(R.string.name_not_available))
    }

    override fun sendUserDetails(user: UserInfo) {
        val intent = Intent(this, UserDetailsScreen::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, user)
        startActivity(intent)
    }

    override fun openInWeb(string: String) {
        val url = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}
