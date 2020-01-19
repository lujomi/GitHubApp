package com.example.lmimica.githubapp.presentation.userdetailscreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.Constants
import com.example.lmimica.githubapp.R
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

    override fun setUserType(string: String?) {
        userType.text = resources.getString(
            R.string.user_type,
            string ?: resources.getString(R.string.info_not_available)
        )
    }

    override fun setUserNodeId(string: String?) {
        nodeId.text = resources.getString(
            R.string.user_node_id,
            string ?: resources.getString(R.string.info_not_available)
        )
    }

    override fun setUserImage(string: String?) {
        Glide.with(this)
            .load(string)
            .fallback(R.drawable.no_image)
            .into(userDetailsImage)
    }

    override fun setUserName(string: String?) {
        userDetailsName.text = resources.getString(
            R.string.user_name,
            string ?: resources.getString(R.string.info_not_available)
        )
    }

    override fun setUserId(string: String?) {
        userDetailsId.text = resources.getString(
            R.string.user_id,
            string ?: resources.getString(R.string.info_not_available)
        )
    }

    override fun openInWeb(string: String) {
        val url = Uri.parse(string)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }

    override fun showWebBtn() {
        btnOpenUserWeb.visibility = View.VISIBLE
    }
}
