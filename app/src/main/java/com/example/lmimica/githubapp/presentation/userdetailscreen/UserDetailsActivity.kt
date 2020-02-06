package com.example.lmimica.githubapp.presentation.userdetailscreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.presentation.UserInfoViewModel
import kotlinx.android.synthetic.main.user_details_activity.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {
    private val userDetailsPresenter: UserDetailsPresenter by inject { (parametersOf(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details_activity)

        userDetailsPresenter.setUserInfo(intent.extras?.getParcelable(Constants.USER_INFO_KEY)!!)

        btnOpenUserWeb.setOnClickListener {
            userDetailsPresenter.openUserInWeb()
        }
    }

    override fun onDestroy() {
        userDetailsPresenter.detach()
        super.onDestroy()
    }

    override fun setUserFields(userInfo: UserInfoViewModel) {
        Glide.with(this)
            .load(userInfo.userImage)
            .fallback(R.drawable.no_image)
            .into(userDetailsImage)

        userType.text = resources.getString(
            R.string.user_type,
            userInfo.userType
        )

        nodeId.text = resources.getString(
            R.string.user_node_id,
            userInfo.userNodeId
        )

        userDetailsName.text = resources.getString(
            R.string.user_name,
            userInfo.userName
        )

        userDetailsId.text = resources.getString(
            R.string.user_id,
            userInfo.userId
        )

        if (userInfo.userUrl != null) {
            btnOpenUserWeb.visibility = View.VISIBLE
        }
    }

    override fun openInWeb(string: String) {
        val url = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }
}
