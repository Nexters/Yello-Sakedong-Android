package com.yello.nexters.yellosakedong.main

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.BaseActivity
import com.yello.nexters.yellosakedong.input.InputActivity
import com.yello.nexters.yellosakedong.output.OutputActivity
import com.yello.nexters.yellosakedong.utils.SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD
import com.yello.nexters.yellosakedong.utils.dot_porgress_bar.SpotsDialog
import com.yello.nexters.yellosakedong.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*


class MainActivity : BaseActivity() {
    override fun getToolbar(): Toolbar = toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        movingImage()
        event()
    }

    fun dialog() {
        val dialog = SpotsDialog.Builder().setMessage("test").setContext(this).build()
        dialog.show()
    }

    private fun movingImage() {
        val titles = resources.getStringArray(R.array.countries)
        val colors = resources.obtainTypedArray(R.array.colors)
        val images = resources.obtainTypedArray(R.array.images)

        picker_main.adapter = object : BubblePickerAdapter {

            override val totalCount = titles.size

            override fun getItem(position: Int): PickerItem {
                return PickerItem().apply {

                    title = titles[position]
                    gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0),
                            colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL)
                    textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                    backgroundImage = ContextCompat.getDrawable(this@MainActivity, images.getResourceId(position, 0))
                }
            }
        }

        picker_main.centerImmediately = true

        picker_main.listener = object : BubblePickerListener {
            override fun onBubbleSelected(item: PickerItem) {
                page404()
                log(item.title)
            }

            override fun onBubbleDeselected(item: PickerItem) {

            }
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.image_emoticon -> {
//                val it = Intent(this, OutputActivity::class.java)
//                startActivity(it)
                dialog()
            }
        }
    }

    private fun event() {

        edit_main_word.viewTreeObserver.addOnGlobalLayoutListener {
            if (isKeyboardShown(edit_main_word.rootView)) {
                layout_emoticon.visibility = View.VISIBLE
                layout_emoticon.animate().translationY(-50f).duration = 1000
                picker_main.visibility = View.GONE
                layout_moving_image.visibility = View.GONE
            } else {
                layout_emoticon.visibility = View.GONE
                layout_emoticon.translationY = 100f
                picker_main.visibility = View.VISIBLE
                layout_moving_image.visibility = View.VISIBLE
            }
        }

        txt_toolbar_add_taste.setOnClickListener { addPage() }
    }

    private fun isKeyboardShown(rootView: View): Boolean {

        val r = Rect()
        rootView.getWindowVisibleDisplayFrame(r)
        val dm = rootView.resources.displayMetrics
        val heightDiff = rootView.bottom - r.bottom

        return heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density
    }

    override fun onResume() {
        super.onResume()
        picker_main.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker_main.onPause()
    }
}
