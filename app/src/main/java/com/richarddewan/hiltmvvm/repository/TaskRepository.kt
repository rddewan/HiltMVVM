package com.richarddewan.hiltmvvm.repository

import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.util.ResultState
import kotlinx.coroutines.flow.Flow


/*
created by Richard Dewan 13/10/2021
*/

interface TaskRepository {

    suspend fun getTasks(): Flow<ResultState<List<TaskLocalEntity>>>
}