package com.yello.nexters.yellosakedong.input

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.yello.nexters.yellosakedong.base.BaseActivity
import com.yello.nexters.yellosakedong.R
import kotlinx.android.synthetic.main.base_toolbar.*

class InputActivity : BaseActivity() {
    override fun getToolbar(): Toolbar = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        initToolBar()
        visibleBackButton(true)
    }
}
