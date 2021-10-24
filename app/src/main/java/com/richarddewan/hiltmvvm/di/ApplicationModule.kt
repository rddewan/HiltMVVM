package com.richarddewan.hiltmvvm.di

import android.content.Context
import androidx.room.Room
import com.richarddewan.hiltmvvm.data.local.dao.TaskDao
import com.richarddewan.hiltmvvm.data.local.db.DatabaseService
import com.richarddewan.hiltmvvm.data.local.entity.TaskEntityMapper
import com.richarddewan.hiltmvvm.data.remote.NetworkBuilder
import com.richarddewan.hiltmvvm.data.remote.NetworkService
import com.richarddewan.hiltmvvm.repository.TaskRepositoryImpl
import com.richarddewan.hiltmvvm.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabaseService(@ApplicationContext context: Context): DatabaseService {
        return  Room.databaseBuilder(
            context,
            DatabaseService::class.java,
            AppConstant.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTasDao(databaseService: DatabaseService): TaskDao {
        return  databaseService.taskDao()
    }

    @Provides
    @Singleton
    fun provideNetworkService(@ApplicationContext context: Context): NetworkService {
        return NetworkBuilder.create(
            "https://freeapi.rdewan.dev/",
            context.cacheDir,
            (10 * 1024 * 1024).toLong()
        )
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        networkService: NetworkService,
        taskDao: TaskDao,
        taskEntityMapper: TaskEntityMapper
    ): TaskRepositoryImpl {
        return TaskRepositoryImpl(
            networkService, taskDao, taskEntityMapper
        )
    }
}