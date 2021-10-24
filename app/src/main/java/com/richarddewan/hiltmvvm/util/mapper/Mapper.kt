package com.richarddewan.hiltmvvm.util.mapper


/*
created by Richard Dewan 13/10/2021
*/

interface Mapper<Entity,Response> {

    fun mapToEntity(response: Response): Entity

    fun mapToEntityList(responses: List<Response>): List<Entity>
}