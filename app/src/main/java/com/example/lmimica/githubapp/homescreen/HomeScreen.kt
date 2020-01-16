package com.example.lmimica.githubapp.homescreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsScreen
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsScreen
import kotlinx.android.synthetic.main.home_screen.*

class HomeScreen : AppCompatActivity(), HomeContract.View, HomeScreenAdapter.UserClickListener,
    RadioGroup.OnCheckedChangeListener {

    private val homeScreenPresenter =
        HomeScreenPresenter()

    private var sortQuery: String = Constants.SORT_BY_FORKS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        actionBar?.hide()
        val toolbar = findViewById(R.id.toolbar_custom) as Toolbar
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)

        homeScreenPresenter.attach(this)

        sendQueryBtn.setOnClickListener {
            homeScreenPresenter.sendRequest(queryEditText.text.toString(), sortQuery)
        }

        sortGroupBtn.setOnCheckedChangeListener(this)
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
                this
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
        sortGroupBtn.visibility = View.VISIBLE
    }

    override fun userDetailsClicked(repository: Repository) {
        homeScreenPresenter.onUserDetailsClicked(repository)
    }

    override fun repoositoryDetailsClicked(repository: Repository) {
        homeScreenPresenter.onRepositoryDetailsClicked(repository)
    }

    override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
        when {
            btnSortForks.id == checkedId -> {
                sortQuery = Constants.SORT_BY_FORKS
                homeScreenPresenter.sendRequest(
                    queryEditText.text.toString(),
                    sortQuery
                )
            }
            btnSortStars.id == checkedId -> {
                sortQuery = Constants.SORT_BY_STARS
                homeScreenPresenter.sendRequest(
                    queryEditText.text.toString(),
                    sortQuery
                )
            }
            else -> {
                sortQuery = Constants.SORT_BY_UPDATES
                homeScreenPresenter.sendRequest(
                    queryEditText.text.toString(),
                    sortQuery
                )
            }
        }
    }
}
