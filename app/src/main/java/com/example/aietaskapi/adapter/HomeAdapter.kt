package com.example.aietaskapi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aietaskapi.databinding.HomeItemViewBinding
import com.example.aietaskapi.helper.AllConstants
import com.example.aietaskapi.model.Employee

class HomeAdapter(private val click: (Int)-> Unit?, private val context: Context) :
    ListAdapter<Employee, HomeAdapter.CategoryViewHolder>(CategoryDiffCallback()) {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HomeItemViewBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), context)
        holder.itemView.setOnClickListener { click(position) }
    }

    class CategoryViewHolder(private val binding: HomeItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: Employee, context: Context) {
            binding.model = model
            binding.tvDate.text="Updated On: "+  AllConstants.lastDate(context)
             binding.executePendingBindings()
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem  == newItem
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}