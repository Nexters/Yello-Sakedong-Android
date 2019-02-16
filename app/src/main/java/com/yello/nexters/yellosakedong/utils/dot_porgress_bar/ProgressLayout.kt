package com.yello.nexters.yellosakedong.utils.dot_porgress_bar

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.widget.FrameLayout
import android.content.res.TypedArray
import android.os.Build
import android.annotation.TargetApi
import android.app.AlertDialog
import android.util.AttributeSet
import com.yello.nexters.yellosakedong.R


class ProgressLayout : FrameLayout {
    var spotsCount: Int = 0

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr, 0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val a : TypedArray = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.AlertDialog,
                defStyleAttr,
                defStyleRes
        )

        spotsCount = a.getInt(R.styleable.Dialog_DialogSpotCount, DEFAULT_COUNT)
        a.recycle()

    }

    fun getSpotCount() : Int = spotsCount

    companion object {

        private const val DEFAULT_COUNT = 5
    }
}