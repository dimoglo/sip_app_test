package com.example.sipapp.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sipapp.databinding.UserItemBinding
import com.example.sipapp.domain.UserModel

class UserListAdapter(
    var users: List<UserModel>,
    private val onUserClicked: (Int) -> Unit
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position], onUserClicked)
    }

    override fun getItemCount(): Int = users.size

    class UserViewHolder(
        private val binding: UserItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            user: UserModel,
            onUserClicked: (Int) -> Unit
        ) {
            binding.root.text = user.name
            binding.root.setOnClickListener {
                onUserClicked(user.id)
            }
        }
    }
}