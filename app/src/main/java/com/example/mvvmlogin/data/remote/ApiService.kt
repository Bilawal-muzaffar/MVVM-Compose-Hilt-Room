package com.example.mvvmlogin.data.remote

import com.example.mvvmlogin.data.model.Medicine
import retrofit2.http.GET

interface ApiService {

    @GET("v3/4c288080-4170-49dd-9de3-aa1ee378036a")
    suspend fun getMedicines(): Medicine
}