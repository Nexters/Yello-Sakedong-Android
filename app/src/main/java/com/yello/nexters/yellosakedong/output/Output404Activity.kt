package com.yello.nexters.yellosakedong.output

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.BaseActivity
import kotlinx.android.synthetic.main.base_toolbar.*

class Output404Activity : BaseActivity() {
    override fun getToolbar(): Toolbar = toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_output_404_page)
        initToolBar()
        visibleBackButton(true)
        txt_toolbar_add_taste.setOnClickListener { addPage() }
    }
}