package com.yello.nexters.yellosakedong.input.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yello.nexters.yellosakedong.R


class InputFragmentTwo : Fragment() {

    companion object {
        fun newInstance() : InputFragmentTwo {
            return  InputFragmentTwo()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image_two, container, false)
    }
}