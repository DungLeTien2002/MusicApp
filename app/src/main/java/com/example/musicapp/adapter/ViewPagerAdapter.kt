package com.example.musicapp.adapter

import android.location.GnssAntennaInfo.Listener
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicapp.screen.explore.ExploreFragment
import com.example.musicapp.screen.home.HomeFragment
import com.example.musicapp.screen.library.LibraryFragment
import com.example.musicapp.screen.profile.ProfileFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                ExploreFragment()
            }
            2-> {
                LibraryFragment()
            }
            else -> {
                ProfileFragment()
            }
        }
    }
//    fun setOnClickListener(listener:OnItemClickListener){
//        onItem
//    }
}