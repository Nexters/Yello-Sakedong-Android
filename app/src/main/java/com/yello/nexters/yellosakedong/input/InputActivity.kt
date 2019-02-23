package com.yello.nexters.yellosakedong.input

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.yello.nexters.yellosakedong.base.BaseActivity
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.input.adapter.InputAdapter
import com.yello.nexters.yellosakedong.network.NetworkObject
import com.yello.nexters.yellosakedong.network.ServiceModule
import com.yello.nexters.yellosakedong.utils.getYellowSakedongKey
import com.yello.nexters.yellosakedong.utils.log
import com.yello.nexters.yellosakedong.utils.setYellowSakedongKey
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_input.*
import kotlinx.android.synthetic.main.base_toolbar.*

class InputActivity : BaseActivity() {
    override fun getToolbar(): Toolbar = toolbar

    private lateinit var subscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        init()
        event()
    }

    fun init() {
        val adapterViewPager = InputAdapter(supportFragmentManager)
        input_viewpager.adapter = adapterViewPager
        tab_layout.setupWithViewPager(input_viewpager)
    }

    private fun event() {
        image_input_popup_close.setOnClickListener { finish() }
        image_input_popup_plus.setOnClickListener { toast("아직 지원하지 않는 기능이에요.. 조금만 기다려주세요.") }

        button_input_popup_insert.setOnClickListener {
            input()
        }
    }

    private fun input() {

        if(edit_input_popup_food_name.text.isEmpty() || edit_input_popup_taste.text.isEmpty()) {
            toast("음식 이름과 맛을 작성해 주세요.")
            return
        }

        subscription = ServiceModule.restAPI().foodAdd(getYellowSakedongKey(this),
                NetworkObject.FoodBody(
                        edit_input_popup_food_name.text.toString(),
                        "이미지", edit_input_popup_taste.text.toString(), (Math.random() * 10 + 1).toInt()
                ))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            finish()
                        },
                        { err ->
                            toast(err.message!!)
                            log(err)
                        }
                )
    }
}
