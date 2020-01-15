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

        btn_show_user.setOnClickListener {
           repoDetailsPresenter.showUserScreen()
        }

        btn_open_web.setOnClickListener {
           repoDetailsPresenter.openRepoInWeb() }
        }

    override fun setRepositoryName(string: String) {
        repository_name_screen.text = resources.getString(R.string.repo_name, string)
    }

    override fun setProgramLnaguageName(string: String) {
        programming_language_name.text = resources.getString(R.string.programming_language, string)
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
