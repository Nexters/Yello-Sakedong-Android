package com.yello.nexters.yellosakedong.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
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
        startActivity<Output404Activity>()
    }

    fun addPage() {
        startActivity<InputActivity>()
    }

    fun toolBarTitle(title: String? = "") {
        supportActionBar?.title = title
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    inline fun <reified T : Activity>
            Context.startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }

}