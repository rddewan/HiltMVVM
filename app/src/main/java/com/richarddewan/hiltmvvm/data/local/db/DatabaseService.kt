package com.richarddewan.hiltmvvm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.richarddewan.hiltmvvm.data.local.dao.TaskDao
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity


/*
created by Richard Dewan 13/10/2021
*/

@Database(entities = [TaskLocalEntity::class],version = 1, exportSchema = false)
abstract  class DatabaseService: RoomDatabase() {

    abstract fun taskDao(): TaskDao

}