package com.niveon.authentikasilogin.repository

import com.niveon.authentikasilogin.api.ApiService
import com.niveon.authentikasilogin.model.LoginRequest
import com.niveon.authentikasilogin.model.LoginResponse
import com.niveon.authentikasilogin.model.UserListResponse
import com.niveon.authentikasilogin.util.DataStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    class UserRepository(private val apiService: ApiService) {
        suspend fun login(request: LoginRequest): Flow<DataStatus<LoginResponse>> {
            return flow {
                emit(DataStatus.loading())

                val response = apiService.login(request)

                when {
                    response.isSuccessful -> {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            if (response.code() == 200) {

                            }
                            emit(DataStatus.success(loginResponse))
                        } else {
                            emit(DataStatus.error("Response body is null"))
                        }
                    }
                    response.code() == 400 -> {
                        emit(DataStatus.error("User not found. Invalid credentials."))
                    }
                    else -> {
                        emit(DataStatus.error("Request failed with code ${response.code()}"))
                    }
                }
            }.flowOn(Dispatchers.IO)
        }

        suspend fun getUsers(page: Int): Flow<DataStatus<UserListResponse>> {
            return flow {
                emit(DataStatus.loading())

                val response = apiService.getUsers(page)

                when {
                    response.isSuccessful -> {
                        val userListResponse = response.body()
                        if (userListResponse != null) {
                            emit(DataStatus.success(userListResponse))
                        } else {
                            emit(DataStatus.error("Response body is null"))
                        }
                    }
                    else -> {
                        emit(DataStatus.error("Request failed with code ${response.code()}"))
                    }
                }
            }.flowOn(Dispatchers.IO)
        }
    }

}