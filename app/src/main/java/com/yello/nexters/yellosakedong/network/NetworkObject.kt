package com.yello.nexters.yellosakedong.network

import java.io.Serializable


object NetworkObject {
    data class SignupModel(
            val message: String,
            val userId: String
    )

    data class SearchModel(
            val error: Int,
            val data: OutputDataModel,
            val comment: List<CommentDataModel>
    )


    data class OutputDataModel(
            val _id: String,
            val foodName: String,
            val foodImageUrl: String,
            val foodComment: String,
            val foodEmoji: Int,
            val foodLikeCount: Int
    )


    data class CommentDataModel(
            val _id: String,
            val user_id: String,
            val food_id: String,
            val comment: String,
            val foodEmoji: Int,
            val likeCount: Int
    )

    data class CommentModel(
            val error: Int,
            val comments: List<CommentDataModel>
    )

    data class FoodBody(
            val foodName : String,
            val foodImageUrl : String,
            val foodComment : String,
            val foodEmoji : Int
    )
}