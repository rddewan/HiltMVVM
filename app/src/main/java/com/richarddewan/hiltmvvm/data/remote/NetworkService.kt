package com.richarddewan.hiltmvvm.data.remote

import com.richarddewan.hiltmvvm.data.remote.request.TaskNetworkRequest
import com.richarddewan.hiltmvvm.data.remote.response.TaskNetworkResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/*
created by Richard Dewan 13/10/2021
*/

interface NetworkService {

    @GET(Endpoint.GET_ALL_TASK)
    suspend fun getAllTask(): List<TaskNetworkResponse.TaskNetworkResponseItem>

    @POST(Endpoint.ADD_TASK)
    suspend fun  addTask(
        @Body task: TaskNetworkRequest
    ): TaskNetworkResponse.TaskNetworkResponseItem
}