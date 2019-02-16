package com.yello.nexters.yellosakedong.utils.dot_porgress_bar

import android.view.animation.Interpolator


class HesitateInterpolator : Interpolator {

    override fun getInterpolation(input: Float): Float {
        return if (input < 0.5) {
            (Math.pow((input * 2).toDouble(), POW) * 0.5f).toFloat()
        } else {
            (Math.pow(((1-input)*2).toDouble(), POW)*-0.5f).toFloat()
        }
    }

    companion object {
        private const val POW = 1.0 / 2.0
    }

}