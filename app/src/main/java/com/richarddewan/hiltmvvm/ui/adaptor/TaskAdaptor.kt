package com.richarddewan.hiltmvvm.ui.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.richarddewan.hiltmvvm.data.local.entity.TaskLocalEntity
import com.richarddewan.hiltmvvm.databinding.TaskItemViewBinding


/*
created by Richard Dewan 20/10/2021
*/

class TaskAdaptor: RecyclerView.Adapter<TaskAdaptor.TaskViewHolder>(){

    class TaskViewHolder(val binding: TaskItemViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: TaskLocalEntity) {
            binding.txtTitle.text = data.title
            binding.txtBody.text = data.body
            binding.txtStatus.text = data.status
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<TaskLocalEntity>() {
        override fun areItemsTheSame(oldItem: TaskLocalEntity, newItem: TaskLocalEntity): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TaskLocalEntity,
            newItem: TaskLocalEntity
        ): Boolean {
            return  oldItem == newItem
        }
    }

    fun setData(newList: List<TaskLocalEntity>) {
        asyncListDiffer.submitList(newList)
    }

    private val asyncListDiffer = AsyncListDiffer(this,diffUtil)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        asyncListDiffer.currentList[position]?.let {
            holder.bindData(it)
        }
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }
}