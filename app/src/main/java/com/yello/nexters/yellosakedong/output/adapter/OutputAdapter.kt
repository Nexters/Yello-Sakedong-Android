package com.yello.nexters.yellosakedong.output.adapter

import android.content.Context
import android.view.ViewGroup
import com.yello.nexters.yellosakedong.R
import com.yello.nexters.yellosakedong.base.GenericRecyclerAdapter
import com.yello.nexters.yellosakedong.base.OnRecyclerItemClickListener
import com.yello.nexters.yellosakedong.output.model.OutputItem
import com.yello.nexters.yellosakedong.output.viewholder.OutputViewHolder

class OutputAdapter(context:Context, listener: OnRecyclerItemClickListener)
    : GenericRecyclerAdapter<OutputItem, OnRecyclerItemClickListener, OutputViewHolder>(context, listener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutputViewHolder {
        return OutputViewHolder(inflate(R.layout.layout_output_item, parent), getListener()!!)
    }
}