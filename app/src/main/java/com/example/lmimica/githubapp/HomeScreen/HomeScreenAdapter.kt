package com.example.lmimica.githubapp.HomeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmimica.githubapp.Model.User
import com.example.lmimica.githubapp.R

class HomeScreenAdapter(
    val users: List<User>,
    val listener: UserClickListener
) : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    interface UserClickListener {
        fun userDetailsClicked(user: User)
        fun repoositoryDetailsClicked(user: User)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userName = view.findViewById<TextView>(R.id.user_name)
        val userImage = view.findViewById<ImageView>(R.id.user_image)
        val userInfoContainer = view.findViewById<LinearLayout>(R.id.user_info_container)

        init {
            userImage.setOnClickListener {
                var position: Int = adapterPosition
                listener.userDetailsClicked(users[position])
            }

            userInfoContainer.setOnClickListener {
                var position: Int = adapterPosition
                listener.repoositoryDetailsClicked(users[position])
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(users.get(position).avatar_url)
            .into(holder.userImage)


        holder.userName.text = (users.get(position).login)
    }

}