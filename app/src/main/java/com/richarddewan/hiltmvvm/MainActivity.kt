package com.richarddewan.hiltmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.databinding.ActivityMainBinding
import com.richarddewan.hiltmvvm.ui.TaskViewModel
import com.richarddewan.hiltmvvm.ui.adaptor.TaskAdaptor
import com.richarddewan.hiltmvvm.util.ResultState
import com.richarddewan.hiltmvvm.util.event.TaskEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var taskAdaptor: TaskAdaptor
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding!!.root)

        //setup rv
        binding.rvTask.apply {
            layoutManager = linearLayoutManager
            adapter = taskAdaptor
        }

        //observe the live data
        setObservers()
        viewModel.setTasState(TaskEvent.GetTask)
    }

    private fun setObservers() {
        viewModel.state.observe(this, {
            when(it) {
                is ResultState.Loading -> {
                    setProgressbar(true)
                }
                is ResultState.Success -> {
                    setTaskList(it.data)
                    setProgressbar(false)
                }
                is ResultState.Error -> {
                    setError(it.exception.message)
                    setProgressbar(false)
                }
            }
        })
    }

    private fun setTaskList(tasks: List<TaskLocalEntity>) {
        taskAdaptor.setData(tasks)
    }

    private fun setError(error: String?){
        error?.let {
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }
    }

    private fun setProgressbar(isShown: Boolean) {
        binding.progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}