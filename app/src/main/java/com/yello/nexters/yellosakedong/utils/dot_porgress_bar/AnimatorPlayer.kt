package com.yello.nexters.yellosakedong.utils.dot_porgress_bar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet


class AnimatorPlayer(val animator: MutableList<Animator>) : AnimatorListenerAdapter() {

    private var interrupted : Boolean = false

    override fun onAnimationEnd(animation: Animator?) {
        if(!interrupted) animate()
    }

    fun play() {
        animate()
    }

    fun stop() {
        interrupted = true
    }

    private fun animate() {
        val set = AnimatorSet()
        set.playTogether(animator)
        set.addListener(this)
        set.start()
    }
}