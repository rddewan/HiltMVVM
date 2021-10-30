package com.richarddewan.hiltmvvm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.richarddewan.hiltmvvm.R
import com.richarddewan.hiltmvvm.data.remote.request.TaskNetworkRequest
import com.richarddewan.hiltmvvm.databinding.FragmentAddBinding
import com.richarddewan.hiltmvvm.util.ResultState
import com.richarddewan.hiltmvvm.util.event.TaskEvent
import java.sql.ResultSet


/*
created by Richard Dewan 24/10/2021
*/

class AddFragment: Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private  val binding: FragmentAddBinding get() = _binding!!
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observe the live data
        setObserver()

        //fab click listener
        binding.fabSaveTask.setOnClickListener {
            addTask()
        }
    }

    private fun addTask(){
        viewModel.taskRequest.value = TaskNetworkRequest(
            userId = "1",
            title = binding.txtTitle.text.toString(),
            body = binding.txtBody.text.toString(),
            note = binding.txtNote.text.toString(),
            status = "STARTED"
        )

        viewModel.setTasState(TaskEvent.AddTask)
    }

    private fun setObserver(){
        viewModel.state.observe(viewLifecycleOwner, {
            when(it) {
                is ResultState.Loading -> {
                    setProgressbar(true)
                }
                is ResultState.Success -> {
                    setProgressbar(false)
                    cleareText()
                }
                is ResultState.Error -> {
                    setProgressbar(false)
                    setError(it.exception.message)
                }
            }
        })
    }

    private fun setProgressbar(isShown: Boolean) {
        binding.pbAddTask.visibility = if (isShown) View.VISIBLE else View.GONE
    }
    private fun setError(error: String?){
        error?.let {
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }
    }

    private fun cleareText(){
        binding.txtTitle.text?.clear()
        binding.txtBody.text?.clear()
        binding.txtNote.text?.clear()
        binding.txtTitle.requestFocus()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}