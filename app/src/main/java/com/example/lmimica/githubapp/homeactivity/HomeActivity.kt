package com.example.lmimica.githubapp.homeactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.repositorydetailscreen.RepositoryDetailsActivity
import com.example.lmimica.githubapp.userdetailscreen.UserDetailsActivity
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract.View, HomeActivityAdapter.UserClickListener,
    RadioGroup.OnCheckedChangeListener {

    private val homeActivityPresenter: HomeActivityPresenter by inject{(parametersOf(this))}

    private var sortQuery: String = Constants.SORT_BY_FORKS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val toolbar = toolbar_custom
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        setSupportActionBar(toolbar)

        sendQueryBtn.setOnClickListener {
            homeActivityPresenter.sendRequest(queryEditText.text.toString(), sortQuery)
            homeActivityPresenter.setKeyboard()
        }

        sortGroupBtn.setOnCheckedChangeListener(this)
    }

    override fun onDestroy() {
        homeActivityPresenter.detach()
        super.onDestroy()
    }

    override fun showList(repositories: List<Repository>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            HomeActivityAdapter(
                repositories,
                this
            )
    }

    override fun showUsersDetailsScreen(repository: Repository) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, repository.userInfo)
        startActivity(intent)
    }

    override fun showRepositoryDetailsScreen(repository: Repository) {
        val intent = Intent(this, RepositoryDetailsActivity::class.java)
        intent.putExtra(Constants.REPOSITIRY_KEY, repository)
        startActivity(intent)
    }

    override fun setSortButtonsVisibility() {
        sortGroupBtn.visibility = View.VISIBLE
    }

    override fun hideKeyboard() {
        val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun userDetailsClicked(repository: Repository) {
        homeActivityPresenter.onUserDetailsClicked(repository)
    }

    override fun repoositoryDetailsClicked(repository: Repository) {
        homeActivityPresenter.onRepositoryDetailsClicked(repository)
    }

    override fun onCheckedChanged(p0: RadioGroup?, checkedId: Int) {
        sortQuery = when {
            btnSortForks.id == checkedId -> {
                Constants.SORT_BY_FORKS
            }
            btnSortStars.id == checkedId -> {
                Constants.SORT_BY_STARS
            }
            else -> {
                Constants.SORT_BY_UPDATES
            }
        }
        homeActivityPresenter.sendRequest(
            queryEditText.text.toString(),
            sortQuery
        )
    }
}
