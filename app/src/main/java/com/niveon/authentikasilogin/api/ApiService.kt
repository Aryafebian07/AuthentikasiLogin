package com.niveon.authentikasilogin.api

import com.niveon.authentikasilogin.model.LoginResponse
import com.niveon.authentikasilogin.model.UserListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import com.niveon.authentikasilogin.model.LoginRequest as LoginRequest1

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest1): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<UserListResponse>
}
