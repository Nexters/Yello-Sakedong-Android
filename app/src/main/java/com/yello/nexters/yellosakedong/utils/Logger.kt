package com.yello.nexters.yellosakedong.utils

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


fun log(any: Any?){
    Logger.addLogAdapter(AndroidLogAdapter())

    if (any == null) {
        log("null")
        return
    }

    when (any) {
        is Throwable -> any.message?.let { Logger.e(it) }
        else -> Logger.d(any.toString())
    }

}