package com.richarddewan.hiltmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.richarddewan.hiltmvvm.R
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.databinding.FragmentHomeBinding
import com.richarddewan.hiltmvvm.ui.adaptor.TaskAdaptor
import com.richarddewan.hiltmvvm.util.ResultState
import com.richarddewan.hiltmvvm.util.event.TaskEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/*
created by Richard Dewan 24/10/2021
*/

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    @Inject
    lateinit var taskAdaptor: TaskAdaptor
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setProgressbar(isShown: Boolean) {
        binding.progressBar.visibility = if (isShown) View.VISIBLE else View.GONE
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}