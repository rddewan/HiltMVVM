package com.richarddewan.hiltmvvm.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.richarddewan.hiltmvvm.data.local.db.DatabaseService
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/*
created by Richard Dewan 21/11/2021
*/

@RunWith(AndroidJUnit4::class)
class TaskDaoTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var databaseService: DatabaseService
    private lateinit var dao: TaskDao

    @Before
    fun setup(){
        databaseService = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java
        ).allowMainThreadQueries().build()

        dao = databaseService.taskDao()
    }

    @After
    fun tearDown(){
        databaseService.close()
    }

    /*
    given the task entity
    when inserted
    then should return a task list that contain the inserted task
     */
    @Test
    fun given_task_when_insert_then_return_should_contain_task_list() = runBlockingTest {
        val item =  TaskLocalEntity(
            id = 1,
            userId = 1,
            title = "test title 1",
            body = "test body 1",
            note = "test note 1",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        )

        val result = dao.inset(item)
        val itemList  = dao.getTasks()

        assertThat(result).isEqualTo(1)
        assertThat(itemList).contains(item)
    }

    /*
   given the task list entity
   when inserted
   then should return a task list that contain the inserted task
    */
    @Test
    fun given_task_list_when_insert_then_return_should_contain_task() = runBlockingTest {
        val taskList = listOf(TaskLocalEntity(
            id = 1,
            userId = 1,
            title = "test title 1",
            body = "test body 1",
            note = "test note 1",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
            ),
            TaskLocalEntity(
                id = 2,
                userId = 1,
                title = "test title 2",
                body = "test body 2",
                note = "test note 2",
                status = "STARTED",
                createdAt = "",
                updatedAt = ""
            )
        )

        val result = dao.insetMany(taskList)
        val itemList  = dao.getTasks()

        assertThat(result.size).isEqualTo(2)
        assertThat(itemList).contains(TaskLocalEntity(
            id = 2,
            userId = 1,
            title = "test title 2",
            body = "test body 2",
            note = "test note 2",
            status = "STARTED",
            createdAt = "",
            updatedAt = ""
        ))
        assertThat(itemList.size).isEqualTo(taskList.size)
    }



}