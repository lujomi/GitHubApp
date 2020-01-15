package com.example.lmimica.githubapp.homescreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsScreen
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsScreen
import kotlinx.android.synthetic.main.home_screen.*
import timber.log.Timber

class HomeScreen : AppCompatActivity(), HomeContract.View, HomeScreenAdapter.UserClickListener {

    private val homeScreenPresenter =
        HomeScreenPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        actionBar?.hide()
        val toolbar = findViewById(R.id.toolbar_custom) as Toolbar
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)

        homeScreenPresenter.attach(this)

        send_query_btn.setOnClickListener {
            homeScreenPresenter.sendRequest(query_edit_text.text.toString())
        }
    }

    override fun onDestroy() {
        homeScreenPresenter.detach()
        super.onDestroy()
    }

    override fun showList(repositories: List<Repository>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter =
            HomeScreenAdapter(
                repositories,
                this@HomeScreen
            )
    }

    override fun showLogMessage(message: String) {
        Timber.d(message)
    }

    override fun showUsersDetailsScreen(repository: Repository) {
        val intent = Intent(this,UserDetailsScreen::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, repository.userInfo)
        startActivity(intent)
    }

    override fun showRepositoryDetailsScreen(repository: Repository) {
        val intent = Intent(this,RepositoryDetailsScreen::class.java)
        intent.putExtra(Constants.REPOSITIRY_KEY, repository)
        startActivity(intent)
    }

    override fun userDetailsClicked(repository: Repository) {
        homeScreenPresenter.onUserDetailsClicked(repository)
    }

    override fun repoositoryDetailsClicked(repository: Repository) {
        homeScreenPresenter.onRepositoryDetailsClicked(repository)
    }
}
