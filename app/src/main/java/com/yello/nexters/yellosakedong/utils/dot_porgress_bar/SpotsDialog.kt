package com.yello.nexters.yellosakedong.utils.dot_porgress_bar

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StyleRes
import android.support.annotation.StringRes
import android.view.View
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.utils.log
import kotlinx.android.synthetic.main.spots_dialog.*


class SpotsDialog(context: Context, val message: String, theme: Int, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?) : AlertDialog(context, theme) {

    private var size: Int = 0
    private var spots: Array<AnimatedView>? = null
    private var animator: AnimatorPlayer? = null

    class Builder {

        private var context: Context? = null
        private var message: String? = null
        private var messageId: Int = 0
        private var themeId: Int = 0
        private var cancelable = true // default dialog behaviour
        private var cancelListener: DialogInterface.OnCancelListener? = null

        fun setContext(context: Context): Builder {
            this.context = context
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setMessage(@StringRes messageId: Int): Builder {
            this.messageId = messageId
            return this
        }

        fun setTheme(@StyleRes themeId: Int): Builder {
            this.themeId = themeId
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            this.cancelable = cancelable
            return this
        }

        fun setCancelListener(cancelListener: DialogInterface.OnCancelListener): Builder {
            this.cancelListener = cancelListener
            return this
        }

        fun build(): AlertDialog {
            return SpotsDialog(
                    this.context!!,
                    if (messageId != 0) this.context!!.getString(messageId) else message!!,
                    if (themeId != 0) themeId else R.style.SpotsDialogDefault,
                    cancelable,
                    cancelListener
            )
        }
    }


    companion object {

        private const val DELAY: Long = 150
        private const val DURATION : Long = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.spots_dialog)
        setCanceledOnTouchOutside(false)

        initProgress()

    }

    private fun initMessage() {
        if (message.isNotEmpty()) {

        }
    }

    private fun initProgress() {
        size = spots_progress.spotsCount
        spots = Array(size) { AnimatedView(context) }

        val dSize = context.resources.getDimensionPixelSize(R.dimen.spot_size)
        val dProgressWidth = context.resources.getDimensionPixelSize(R.dimen.progress_width)
        for (i in 0 until spots!!.size) {
            val v = AnimatedView(context)
            v.setBackgroundResource(R.drawable.dialog_dot)
            v.setTarget(dProgressWidth)
            v.setXFactor(-1f)
            v.visibility = View.INVISIBLE
            spots_progress.addView(v, dSize, dSize)
            spots!![i] = v
        }
    }

    private fun createAnimations() : MutableList<Animator> {

        val list = ArrayList<Animator>()
        spots?.let {
            for (i in 0 until  it.size) {
                val animatedView = it[i]
                val move: Animator = ObjectAnimator.ofFloat(animatedView, "xFactor",0.0f, 1.0f)
                move.duration = DURATION
                move.interpolator = HesitateInterpolator()
                move.startDelay = DELAY * i
                move.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        animatedView.visibility = View.INVISIBLE
                    }

                    override fun onAnimationStart(animation: Animator?) {
                        super.onAnimationStart(animation)
                        animatedView.visibility = View.VISIBLE
                    }
                })
                list.add(move)
            }
        }

        return list
    }

    override fun onStart() {
        super.onStart()
        this.spots?.let {
            for(view in it) view.visibility = View.VISIBLE
        }
        animator = AnimatorPlayer(createAnimations())
        animator?.play()
    }

    override fun onStop() {
        super.onStop()
        animator?.stop()
    }
}