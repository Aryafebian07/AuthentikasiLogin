package com.niveon.authentikasilogin.model

data class UserListResponse(val data: List<User>){
    data class User(val id: Int, val email: String, val first_name: String, val last_name: String)
}