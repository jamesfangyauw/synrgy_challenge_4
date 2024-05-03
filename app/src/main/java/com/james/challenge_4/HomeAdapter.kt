package com.james.challenge_4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.james.challenge_4.databinding.ItemNoteBinding
import com.james.challenge_4.domain.model.Note

class HomeAdapter(private val context: Context) : ListAdapter<Note, HomeAdapter.ViewHolder>(differCallback) {


    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
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
        fun bind(data: Note, listenerEdit: ((Note) -> Unit)?, listenerDel: ((Note) -> Unit)?){
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

    private var listenerEdit : ((Note) -> Unit)? = null
    fun setOnItemClick(listener: (Note) -> Unit){
        this.listenerEdit = listener
    }
    private var listenerDel : ((Note) -> Unit)? = null
    fun setOnDelClick(listener: (Note) -> Unit){
        this.listenerDel = listener
    }
}