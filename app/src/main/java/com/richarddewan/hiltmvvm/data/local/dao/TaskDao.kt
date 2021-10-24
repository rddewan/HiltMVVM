package com.richarddewan.hiltmvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity


/*
created by Richard Dewan 13/10/2021
*/

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inset(task: TaskLocalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetMany(tasks: List<TaskLocalEntity>): List<Long>

    @Query("SELECT * FROM tasks")
    suspend fun getTasks(): List<TaskLocalEntity>


}