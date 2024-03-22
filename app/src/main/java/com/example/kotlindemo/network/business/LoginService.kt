package com.example.kotlindemo.network.business

import com.example.kotlindemo.network.entity.InfoEntity
import retrofit2.Call
import retrofit2.http.GET

interface LoginService {
    @GET("/api/info")
    fun getInfo(): Call<InfoEntity>

    @GET("/api/info")
    suspend fun getInfoBySuspend(): InfoEntity
}