package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.fragments.SqliteFragmentCreate
import com.example.myapplication.fragments.SqliteFragmentUpdate

class SqlitePageAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> SqliteFragmentCreate()
            else -> SqliteFragmentUpdate()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Create"
            else -> "Update and Delete"
        }
    }
}