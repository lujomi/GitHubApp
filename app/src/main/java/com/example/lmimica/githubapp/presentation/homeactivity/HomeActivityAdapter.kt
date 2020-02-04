package com.example.lmimica.githubapp.presentation.homeactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.R
import com.example.lmimica.githubapp.presentation.RepositoryViewModel
import kotlinx.android.synthetic.main.user_item.view.*

class HomeActivityAdapter(
    val repositories: List<RepositoryViewModel>,
    val listener: UserClickListener
) : RecyclerView.Adapter<HomeActivityAdapter.ViewHolder>() {

    interface UserClickListener {
        fun userDetailsClicked(repository: RepositoryViewModel)
        fun repositoryDetailsClicked(repository: RepositoryViewModel)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.userName
        val userImage: ImageView = view.user_image
        val repositoryName: TextView = view.repositoryName
        val followersNumb: TextView = view.followersNumb
        val forksNumb: TextView = view.forksNumb
        val issuesNumb: TextView = view.issuesNumb
        private val userInfoContainer: LinearLayout = view.user_info_container

        init {
            userImage.setOnClickListener {
                val position: Int = adapterPosition
                listener.userDetailsClicked(repositories[position])
            }

            userInfoContainer.setOnClickListener {
                val position: Int = adapterPosition
                listener.repositoryDetailsClicked(repositories[position])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(repositories[position].userInfo?.userImage)
            .fallback(R.drawable.no_image)
            .into(holder.userImage)

        holder.repositoryName.text = (holder.itemView.context.getString(
            R.string.repository_name,
            repositories[position].repositoryName ?: R.string.info_not_available
        ))
        holder.forksNumb.text = (holder.itemView.context.getString(
            R.string.forks_numb,
            repositories[position].forksNumber ?: R.string.info_not_available
        ))
        holder.issuesNumb.text = (holder.itemView.context.getString(
            R.string.issues_numb,
            repositories[position].issuesNumber ?: R.string.info_not_available
        ))
        holder.followersNumb.text = (holder.itemView.context.getString(
            R.string.followers_numb,
            repositories[position].followersNumber ?: R.string.info_not_available
        ))
        holder.userName.text = (holder.itemView.context.getString(
            R.string.user_name,
            repositories[position].userInfo?.userName ?: R.string.info_not_available
        ))
    }

}