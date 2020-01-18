package com.example.lmimica.githubapp.presentation.repositorydetailscreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.model.UserInfo
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsActivity
import kotlinx.android.synthetic.main.repository_details_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RepositoryDetailsActivity : AppCompatActivity(), RepositoryDetailsContract.View {
    private val repoDetailsPresenter: RepositoryDetailsPresenter by inject {(parametersOf(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details_activity)

        repoDetailsPresenter.setRepository(intent.extras?.getParcelable(Constants.REPOSITIRY_KEY)!!)

        btnShowUser.setOnClickListener {
           repoDetailsPresenter.showUserScreen()
        }

        btnOpenWeb.setOnClickListener {
           repoDetailsPresenter.openRepoInWeb() }
        }

    override fun setRepositoryName(string: String?) {
        repositoryNameScreen.text = resources.getString(R.string.repo_name, string ?: resources.getString(R.string.info_not_available))
    }

    override fun setProgramLnaguageName(string: String?) {
        programmingLanguageName.text = resources.getString(R.string.programming_language, string ?: resources.getString(R.string.info_not_available))
    }

    override fun setRepositoryCreatedDate(string: String) {
        var text = string
        if ("".equals(text)) text = resources.getString(R.string.unknown_date)
        createdDate.text = resources.getString(R.string.created_date, text)
    }

    override fun setRepositoryUpdatedDate(string: String) {
        var text = string
        if(text.isEmpty()) text = resources.getString(R.string.unknown_date)
        updatedDate.text = resources.getString(R.string.updated_date, text)
    }

    override fun sendUserDetails(user: UserInfo) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, user)
        startActivity(intent)
    }

    override fun openInWeb(string: String) {
        val url = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }

    override fun showWebBtn() {
        btnOpenWeb.visibility = View.VISIBLE
    }
}
