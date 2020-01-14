package com.example.lmimica.githubapp.HomeScreen

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lmimica.githubapp.Model.User
import com.example.lmimica.githubapp.MyTextWatcher
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.RepositoryDetailsScreen.RepositoryDetailsScreen
import com.example.lmimica.githubapp.UserDetailsScreen.UserDetailsScreen
import kotlinx.android.synthetic.main.home_screen.*
import timber.log.Timber

class HomeScreen : AppCompatActivity(), HomeContract.View, HomeScreenAdapter.UserClickListener {

    private val homeScreenPresenter =
        HomeScreenPresenter()
    var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        actionBar?.hide()
        val toolbar = findViewById(R.id.toolbar_custom) as Toolbar
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        setSupportActionBar(toolbar)

        homeScreenPresenter.attach(this)

        query_edit_text.addTextChangedListener(object : MyTextWatcher(){
            override fun afterTextChanged(p0: Editable?) {
                query = p0.toString()
            }
        })

        send_query_btn.setOnClickListener {
            homeScreenPresenter.sendRequest(query)
        }
    }

    override fun onDestroy() {
        homeScreenPresenter.detach()
        super.onDestroy()
    }

    override fun showList(users: List<User>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter =
            HomeScreenAdapter(
                users,
                this@HomeScreen
            )
    }

    override fun showLogMessage(message: String) {
        Timber.d(message)
    }

    override fun showUsersDetailsScreen(user: User) {
        val intent = Intent(this,UserDetailsScreen::class.java)
        startActivity(intent)
        Toast.makeText(this, user.login, Toast.LENGTH_SHORT).show()
    }

    override fun showRepositoryDetailsScreen(user: User) {
        val intent = Intent(this,RepositoryDetailsScreen::class.java)
        startActivity(intent)
        Toast.makeText(this, user.avatar_url, Toast.LENGTH_SHORT).show()
    }

    override fun userDetailsClicked(user: User) {
        homeScreenPresenter.onUserDetailsClicked(user)
    }

    override fun repoositoryDetailsClicked(user: User) {
        homeScreenPresenter.onRepositoryDetailsClicked(user)
    }
}
