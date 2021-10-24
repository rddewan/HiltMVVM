package com.richarddewan.hiltmvvm.data.local.entity

import com.richarddewan.hiltmvvm.data.remote.response.TaskNetworkResponse
import com.richarddewan.hiltmvvm.util.mapper.Mapper
import javax.inject.Inject


/*
created by Richard Dewan 13/10/2021
*/

class TaskEntityMapper @Inject constructor() : Mapper<TaskLocalEntity, TaskNetworkResponse.TaskNetworkResponseItem> {

    override fun mapToEntity(response: TaskNetworkResponse.TaskNetworkResponseItem): TaskLocalEntity {
        return  TaskLocalEntity(
            id = response.id.toInt(),
            userId = response.userId.toInt(),
            title = response.title,
            body = response.body,
            status = response.status,
            note = response.note,
            createdAt = response.createdAt,
            updatedAt = response.updatedAt
        )
    }

    override fun mapToEntityList(responses: List<TaskNetworkResponse.TaskNetworkResponseItem>): List<TaskLocalEntity> {
        return responses.map {
            mapToEntity(it)
        }
    }
}