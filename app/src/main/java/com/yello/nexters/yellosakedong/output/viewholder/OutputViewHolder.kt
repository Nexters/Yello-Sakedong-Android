package com.yello.nexters.yellosakedong.output.viewholder

import android.content.res.Resources
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.View
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.BaseViewHolder
import com.yello.nexters.yellosakedong.base.OnRecyclerItemClickListener
import com.yello.nexters.yellosakedong.output.model.OutputItem
import kotlinx.android.synthetic.main.layout_output_item.view.*

class OutputViewHolder(itemView: View, listener: OnRecyclerItemClickListener) :BaseViewHolder<OutputItem, OnRecyclerItemClickListener>(itemView, listener) {

    init {

    }

    override fun onBind(item: OutputItem) {
        itemView.let {
            if(item.isOwner) {
                it.layout_output_item.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.mainYellow))
                it.image_output_delete.visibility = View.VISIBLE
            }
            when {
                item.index == 0 -> {
                    it.layout_output_rank.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.mainYellow))
                    it.layout_output_item.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.brightGray))
                }
                item.index == 1 -> {
                    it.layout_output_rank.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.darkGreen))
                    it.layout_output_item.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.brightGray))
                }
                item.index == 2 -> {
                    it.layout_output_rank.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.brightBlue))
                    it.layout_output_item.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.brightGray))
                }
                else -> {
                    it.layout_output_rank.visibility = View.GONE
                }
            }
            it.txt_output_user_name.text = item.userName
            it.txt_output_comment.text = item.comment
            it.txt_output_like_count.text = item.likeCount.toString()
            if(item.isLike) {
                it.image_output_like.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_thumb_up_orange_24dp)
            } else {
                it.image_output_like.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_thumb_up_gray_24dp)
            }
        }
    }
}