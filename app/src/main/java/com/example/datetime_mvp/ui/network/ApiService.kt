package com.example.datetime_mvp.ui.network

import com.example.datetime_mvp.ui.model.DateResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v3/73bfab1f-3f94-4996-95a5-0984e3374037")
    suspend fun getCurrentDateTime() : DateResponse
}


//val apiService = object : ApiService {
//    override fun getCurrentDateTime(): Call<DateResponse> {
//        Thread.sleep(100)
//        return Call<DateResponse(currentDateTime = "2025-05-20T20:11:00")>
//    }
//}