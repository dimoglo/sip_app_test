package com.example.sipapp.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sipapp.ui.user_list.UserListFragment
import com.example.sipapp.ui.video.VideoFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            VideoFragment.TAB_POSITION -> VideoFragment()
            UserListFragment.TAB_POSITION -> UserListFragment()
            else -> throw IllegalStateException("Position not valid")
        }
    }
}