package com.yello.nexters.yellosakedong.output.model

data class OutputItem(
        val index: Int,
        val userName: String,
        val likeCount: Int,
        val comment: String,
        val isOwner: Boolean,
        val isLike: Boolean
)