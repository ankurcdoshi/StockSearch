package com.angel.stocksearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angel.stocksearch.R
import com.angel.stocksearch.rest.model.User
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*

class FriendsAdapter(private val mFriends: List<User>): RecyclerView.Adapter<FriendHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendHolder {
        return FriendHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mFriends.size
    }

    override fun onBindViewHolder(holder: FriendHolder, position: Int) {
        val user = mFriends[position]
        holder.name.text = user.display_name
        Glide.with(holder.itemView.context).load(user.avatar_url).into(holder.profilePic)
    }
}

class FriendHolder(view: View): RecyclerView.ViewHolder(view) {
    val name: TextView = view.txt_name
    val profilePic: ImageView = view.img_photo
}