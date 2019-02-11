package com.yello.nexters.yellosakedong.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.yello.nexters.yellosakedong.input.InputActivity
import com.yello.nexters.yellosakedong.output.Output404Activity

abstract class BaseActivity : AppCompatActivity() {


    protected abstract fun getToolbar(): Toolbar

    fun visibleBackButton(isVisible: Boolean) {

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(isVisible)
            it.setDisplayShowHomeEnabled(isVisible)
        }

        getToolbar().setNavigationOnClickListener {
            finish()
        }
    }

    fun initToolBar() {
        setSupportActionBar(getToolbar())
        toolBarTitle()
    }

    fun page404() {
        startActivity(Intent(this, Output404Activity::class.java))
    }

    fun addPage() {
        startActivity(Intent(this, InputActivity::class.java))
    }

    fun toolBarTitle(title: String? = "") {
        supportActionBar?.title = title
    }
}