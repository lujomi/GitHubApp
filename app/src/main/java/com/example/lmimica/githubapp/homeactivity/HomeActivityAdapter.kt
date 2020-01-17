package com.example.lmimica.githubapp.homeactivity

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

class HomeActivityAdapter(
    val repositories: List<Repository>,
    val listener: UserClickListener
) : RecyclerView.Adapter<HomeActivityAdapter.ViewHolder>() {

    interface UserClickListener {
        fun userDetailsClicked(repository: Repository)
        fun repoositoryDetailsClicked(repository: Repository)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName: TextView = view.findViewById(R.id.userName)
        val userImage: ImageView = view.findViewById(R.id.user_image)
        val repositoryName: TextView = view.findViewById(R.id.repositoryName)
        val followersNumb: TextView = view.findViewById(R.id.followersNumb)
        val forksNumb: TextView = view.findViewById(R.id.forksNumb)
        val issuesNumb: TextView = view.findViewById(R.id.issuesNumb)
        private val userInfoContainer: LinearLayout = view.findViewById(R.id.user_info_container)

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
            .load(repositories[position].userInfo?.userImage)
            .fallback(R.drawable.no_image)
            .into(holder.userImage)

        holder.repositoryName.text = (holder.itemView.context.getString(R.string.repo_name, repositories[position].repositoryName ?: R.string.name_not_available))
        holder.forksNumb.text = (holder.itemView.context.getString(R.string.forks_numb, repositories[position].forksNumber ?: R.string.number_not_available))
        holder.issuesNumb.text = (holder.itemView.context.getString(R.string.issues_numb, repositories[position].issuesNumber ?: R.string.number_not_available))
        holder.followersNumb.text = (holder.itemView.context.getString(R.string.followers_numb, repositories[position].followersNumber ?: R.string.number_not_available))
        holder.userName.text = (holder.itemView.context.getString(R.string.user_name, repositories[position].userInfo?.userName ?: R.string.name_not_available))
    }

}