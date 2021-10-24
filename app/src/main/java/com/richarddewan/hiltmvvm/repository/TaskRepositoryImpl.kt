package com.richarddewan.hiltmvvm.repository

import com.richarddewan.hiltmvvm.data.local.dao.TaskDao
import com.richarddewan.hiltmvvm.data.local.entity.TaskEntityMapper
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.data.remote.NetworkService
import com.richarddewan.hiltmvvm.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


/*
created by Richard Dewan 13/10/2021
*/

class TaskRepositoryImpl(
    private val networkService: NetworkService,
    private val taskDao: TaskDao,
    private val taskEntityMapper: TaskEntityMapper
): TaskRepository {

    override suspend fun getTasks(): Flow<ResultState<List<TaskLocalEntity>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = networkService.getAllTask()
            val taskList = taskEntityMapper.mapToEntityList(response)
            taskDao.insetMany(taskList)

            val task = taskDao.getTasks()
            emit(ResultState.Success(task))
        }
        catch (e: Exception) {
            emit(ResultState.Error(e))
        }

    }
}