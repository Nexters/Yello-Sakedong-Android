package com.yello.nexters.yellosakedong.base

import android.icu.lang.UCharacter.GraphemeClusterBreak.T



interface OnRecyclerObjectClickListener<T> : BaseRecyclerListener {
    fun onItemClicked(item: T)
}

interface OnRecyclerItemClickListener: BaseRecyclerListener {
    fun onItemClick(position: Int)
}