package com.yello.nexters.yellosakedong.input.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yello.nexters.yellosakedong.input.fragment.InputFragmentOne
import com.yello.nexters.yellosakedong.input.fragment.InputFragmentTwo


class InputAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> {
                return InputFragmentOne()
            }
            1 -> {
                return InputFragmentTwo()
            }
        }
        return null
    }

    override fun getCount(): Int = 2

}