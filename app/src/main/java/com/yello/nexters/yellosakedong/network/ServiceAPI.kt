package com.yello.nexters.yellosakedong.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServiceAPI {
    @POST("user/signup")
    fun signUp(@Header("yellowSakeongKey") key: String): Observable<NetworkObject.signupModel>
}