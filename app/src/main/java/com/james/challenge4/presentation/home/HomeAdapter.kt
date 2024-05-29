package com.james.challenge4.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.james.challenge4.databinding.ItemNoteBinding
import com.james.challenge4.presentation.NoteParcelize

class HomeAdapter(private val context: Context) : ListAdapter<NoteParcelize, HomeAdapter.ViewHolder>(
    differCallback
) {


    companion object {
        val differCallback = object : DiffUtil.ItemCallback<NoteParcelize>(){
            override fun areItemsTheSame(oldItem: NoteParcelize, newItem: NoteParcelize): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: NoteParcelize, newItem: NoteParcelize): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) holder.bind(currentItem, listenerEdit, listenerDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    inner class ViewHolder(private val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NoteParcelize, listenerEdit: ((NoteParcelize) -> Unit)?, listenerDel: ((NoteParcelize) -> Unit)?){
            with(binding){
                tvTitleNote.text = data.title.toString()
                tvContent.text = data.content.toString()
                btnEdit.setOnClickListener {
                    listenerEdit?.let {
                        listenerEdit(data)
                    }
                }
                btnDelete.setOnClickListener {
                    listenerDel?.let {
                        listenerDel(data)
                    }
                }
            }
        }
    }

    private var listenerEdit : ((NoteParcelize) -> Unit)? = null
    fun setOnItemClick(listener: (NoteParcelize) -> Unit){
        this.listenerEdit = listener
    }
    private var listenerDel : ((NoteParcelize) -> Unit)? = null
    fun setOnDelClick(listener: (NoteParcelize) -> Unit){
        this.listenerDel = listener
    }
}