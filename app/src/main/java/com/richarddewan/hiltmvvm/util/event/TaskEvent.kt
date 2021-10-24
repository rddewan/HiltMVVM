package com.richarddewan.hiltmvvm.util.event

sealed class TaskEvent {
    object GetTask: TaskEvent()
    object AddTask: TaskEvent()
    object DeleteTask: TaskEvent()
    object None: TaskEvent()
}
