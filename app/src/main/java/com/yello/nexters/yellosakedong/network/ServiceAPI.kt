package com.yello.nexters.yellosakedong.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ServiceAPI {
    @POST("user/signup")
    fun signUp(@Header("yellowSakeongKey") key: String): Observable<NetworkObject.SignupModel>


    @GET("output")
    fun output(@Header("_id") userId: String, @Query("inputFoodName") foodName: String): Observable<NetworkObject.SearchModel>

    @GET("output/comments")
    fun comments(@Header("_id") userId: String, @Query("foodId") foodId: String) : Observable<NetworkObject.CommentModel>
}