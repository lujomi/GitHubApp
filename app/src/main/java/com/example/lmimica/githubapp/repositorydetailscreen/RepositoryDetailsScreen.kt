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
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details_screen)

        repoDetailsPresenter.attach(this)

        repository = intent.extras?.getParcelable<Repository>(Constants.REPOSITIRY_KEY)!!

        repository.let { repoDetailsPresenter.repositoryReceived(it) }

        btn_show_user.setOnClickListener {
            repository.userInfo?.let { it1 -> repoDetailsPresenter.showUserScreen(it1) } //da sam dole u sendUserDetails posla direktno repository.userInfo ovde ne bi ima parametara u metodi? Šta je bolje
        }

        btn_open_web.setOnClickListener {
            repository.repositoryUrl?.let { it1 -> repoDetailsPresenter.openRepoInWeb(it1) }
        }
    }

    override fun onDestroy() {
        repoDetailsPresenter.detach()
        super.onDestroy()
    }

    override fun setRepositoryName(string: String) {
        repository_name_screen.text = resources.getString(R.string.repo_name, string)
    }

    override fun setProgramLnaguageName(string: String) {
        programming_language_name.text = resources.getString(R.string.programming_language, string)
    }

    override fun sendUserDetails(userInfo: UserInfo) {
        val intent = Intent(this, UserDetailsScreen::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, userInfo) //ovde mogu poslat i direktno repository.userInfo i onda metode nemaju parametara. Šta je bolje
        startActivity(intent)
    }

    override fun openInWeb(repositoryUrl: String) {
        val url = Uri.parse(repositoryUrl)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}
