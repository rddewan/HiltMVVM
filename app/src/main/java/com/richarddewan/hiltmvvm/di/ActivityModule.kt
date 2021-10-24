package com.richarddewan.hiltmvvm.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.richarddewan.hiltmvvm.ui.adaptor.TaskAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @Provides
    fun provideTaskAdaptor(): TaskAdaptor {
        return TaskAdaptor()
    }

    @Provides
    fun provideLinearLayoutManager(@ActivityContext context: Context) = LinearLayoutManager(context)
}