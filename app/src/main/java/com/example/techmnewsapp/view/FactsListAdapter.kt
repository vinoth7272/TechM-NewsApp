package com.example.techmnewsapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.techmnewsapp.data.model.Facts
import com.example.techmnewsapp.databinding.ListItemBinding

class FactsListAdapter : RecyclerView.Adapter<FactsListAdapter.ViewHolder>() {

    private var newsList = ArrayList<Facts>()
    fun setData(factsList: ArrayList<Facts>) {
        this.newsList = factsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount() = newsList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(newsList[position])

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(facts: Facts) {
            binding.facts = facts
            binding.executePendingBindings()
        }
    }
}