package com.example.lmimica.githubapp.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.model.Repository
import com.example.lmimica.githubapp.R

class HomeScreenAdapter(
    val repositories: List<Repository>,
    val listener: UserClickListener
) : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    interface UserClickListener {
        fun userDetailsClicked(repository: Repository)
        fun repoositoryDetailsClicked(repository: Repository)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.userName)
        val userImage = view.findViewById<ImageView>(R.id.user_image)
        val repositoryName = view.findViewById<TextView>(R.id.repositoryName)
        val followersNumb = view.findViewById<TextView>(R.id.followersNumb)
        val forksNumb = view.findViewById<TextView>(R.id.forksNumb)
        val issuesNumb = view.findViewById<TextView>(R.id.issuesNumb)
        val userInfoContainer = view.findViewById<LinearLayout>(R.id.user_info_container)

        init {
            userImage.setOnClickListener {
                val position: Int = adapterPosition
                listener.userDetailsClicked(repositories[position])
            }

            userInfoContainer.setOnClickListener {
                val position: Int = adapterPosition
                listener.repoositoryDetailsClicked(repositories[position])
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
            .load(repositories.get(position).userInfo?.userImage)
            .fallback(R.drawable.no_image)
            .into(holder.userImage)

        holder.repositoryName.text = (holder.itemView.context.getString(R.string.repo_name, repositories.get(position).repositoryName ?: R.string.name_not_available))
        holder.forksNumb.text = (holder.itemView.context.getString(R.string.forks_numb, repositories.get(position).forksNumber ?: R.string.number_not_available))
        holder.issuesNumb.text = (holder.itemView.context.getString(R.string.issues_numb, repositories.get(position).issuesNumber ?: R.string.number_not_available))
        holder.followersNumb.text = (holder.itemView.context.getString(R.string.followers_numb, repositories.get(position).followersNumber ?: R.string.number_not_available))
        holder.userName.text = (holder.itemView.context.getString(R.string.user_name, repositories.get(position).userInfo?.userName ?: R.string.name_not_available))
    }

}