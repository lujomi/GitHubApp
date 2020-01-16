package com.example.lmimica.githubapp.homescreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsScreen
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsScreen
import kotlinx.android.synthetic.main.home_screen.*

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

        sendQueryBtn.setOnClickListener {
            homeScreenPresenter.sendRequest(queryEditText.text.toString(), Constants.SORT_BY_FORKS)
        }

        sortGroupBtn.setOnCheckedChangeListener { radioGroup, checkedId ->
            if(btnSortForks.id == checkedId){
                homeScreenPresenter.sendRequest(queryEditText.text.toString(), Constants.SORT_BY_FORKS)
            } else if(btnSortStars.id == checkedId) {
                homeScreenPresenter.sendRequest(queryEditText.text.toString(), Constants.SORT_BY_STARS)
            } else {
                homeScreenPresenter.sendRequest(queryEditText.text.toString(), Constants.SORT_BY_UPDATES)
            }
        }
    }

    override fun onDestroy() {
        homeScreenPresenter.detach()
        super.onDestroy()
    }

    override fun showList(repositories: List<Repository>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            HomeScreenAdapter(
                repositories,
                this@HomeScreen
            )
    }

    override fun showUsersDetailsScreen(repository: Repository) {
        val intent = Intent(this, UserDetailsScreen::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, repository.userInfo)
        startActivity(intent)
    }

    override fun showRepositoryDetailsScreen(repository: Repository) {
        val intent = Intent(this, RepositoryDetailsScreen::class.java)
        intent.putExtra(Constants.REPOSITIRY_KEY, repository)
        startActivity(intent)
    }

    override fun setSortButtonsVisibility() {
        btnSortForks.visibility = View.VISIBLE
        btnSortStars.visibility = View.VISIBLE
        btnSortUpdated.visibility = View.VISIBLE
    }

    override fun userDetailsClicked(repository: Repository) {
        homeScreenPresenter.onUserDetailsClicked(repository)
    }

    override fun repoositoryDetailsClicked(repository: Repository) {
        homeScreenPresenter.onRepositoryDetailsClicked(repository)
    }
}
