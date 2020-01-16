package com.example.lmimica.githubapp.userdetailscreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.model.UserInfo
import kotlinx.android.synthetic.main.activity_user_details_screen.*

class UserDetailsScreen : AppCompatActivity(), UserDetailsContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_screen)

        val userDetailsPresenter = UserDetailsPresenter()

        userDetailsPresenter.attach(this)
        userDetailsPresenter.setUserInfo(intent.extras?.getParcelable<UserInfo>(Constants.USER_INFO_KEY)!!)

        btnOpenUserWeb.setOnClickListener {
            userDetailsPresenter.openUserInWeb()
        }
    }

    override fun setUserImage(string: String?) {
        Glide.with(this)
            .load(string)
            .fallback(R.drawable.no_image)
            .into(userDetailsImage)
    }

    override fun setUserName(string: String?) {
        userDetailsName.text = resources.getString(R.string.repo_name, string ?: resources.getString(R.string.name_not_available))
    }

    override fun setUserId(string: String?) {
        UserDetailsId.text = resources.getString(R.string.user_id, string ?: resources.getString(R.string.name_not_available))
    }

    override fun openInWeb(string: String) {
        val url = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}
