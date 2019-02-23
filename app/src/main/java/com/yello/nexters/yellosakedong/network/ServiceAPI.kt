package com.yello.nexters.yellosakedong.network

import io.reactivex.Observable
import retrofit2.http.*

interface ServiceAPI {
    @POST("user/signup")
    fun signUp(@Header("yellowSakeongKey") key: String): Observable<NetworkObject.SignupModel>


    @GET("output")
    fun output(@Header("_id") userId: String, @Query("inputFoodName") foodName: String): Observable<NetworkObject.SearchModel>

    @GET("output/comments")
    fun comments(@Header("_id") userId: String, @Query("foodId") foodId: String) : Observable<NetworkObject.CommentModel>

    @POST("food")
    fun foodAdd(@Header("_id") userId: String, @Body body: NetworkObject.FoodBody) : Observable<NetworkObject.FoodBody>
}