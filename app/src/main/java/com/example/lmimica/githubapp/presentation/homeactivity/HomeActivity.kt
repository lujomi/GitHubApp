package com.example.lmimica.githubapp.presentation.homeactivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.presentation.RepositoryViewModel
import com.example.lmimica.githubapp.presentation.repositorydetailscreen.RepositoryDetailsActivity
import com.example.lmimica.githubapp.presentation.userdetailscreen.UserDetailsActivity
import kotlinx.android.synthetic.main.home_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract.View, HomeActivityAdapter.UserClickListener,
    RadioGroup.OnCheckedChangeListener {

    private val homeActivityPresenter: HomeActivityPresenter by inject { (parametersOf(this)) }

    private var sortQuery: String = Constants.SORT_BY_FORKS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        sendQueryBtn.setOnClickListener {
            homeActivityPresenter.sendRequest(queryEditText.text.toString(), sortQuery)
        }

        sortGroupBtn.setOnCheckedChangeListener(this)
    }

    override fun onDestroy() {
        homeActivityPresenter.detach()
        super.onDestroy()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showList(repositories: List<RepositoryViewModel>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter =
            HomeActivityAdapter(
                repositories,
                this
            )
    }

    override fun showUsersDetailsScreen(repository: RepositoryViewModel) {
        val intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra(Constants.USER_INFO_KEY, repository.userInfo)
        startActivity(intent)
    }

    override fun showRepositoryDetailsScreen(repository: RepositoryViewModel) {
        val intent = Intent(this, RepositoryDetailsActivity::class.java)
        intent.putExtra(Constants.REPOSITIRY_KEY, repository)
        startActivity(intent)
    }

    override fun setSortButtonsVisibility() {
        sortGroupBtn.visibility = View.VISIBLE
    }

    override fun hideKeyboard() {
        val inputMethodManager =
            this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun userDetailsClicked(repository: RepositoryViewModel) {
        homeActivityPresenter.onUserDetailsClicked(repository)
    }

    override fun repositoryDetailsClicked(repository: RepositoryViewModel) {
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
