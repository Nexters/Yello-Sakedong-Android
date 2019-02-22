package com.yello.nexters.yellosakedong.network

import android.content.Context
import android.net.ConnectivityManager
import com.yello.nexters.yellosakedong.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object ServiceModule {

    fun restAPI (): ServiceAPI {
        return retrofitInterface().create(ServiceAPI::class.java)
    }

    private val httpClient = OkHttpClient.Builder()
            .addNetworkInterceptor { chain: Interceptor.Chain -> chain.proceed(chain.request().newBuilder().build()) }!!

    private fun retrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient.build())
                .build()
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}