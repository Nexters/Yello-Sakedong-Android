package com.yello.nexters.yellosakedong.utils.dot_porgress_bar

import android.content.Context
import android.view.View


class AnimatedView(context: Context) : View(context) {

    private var target: Int = 0

    fun getXFactor(): Float = x / target

    fun setXFactor(xFactor: Float) {
        x = target * xFactor
    }

    fun setTarget(target: Int) {
        this.target = target
    }

    fun getTarget() = target
}