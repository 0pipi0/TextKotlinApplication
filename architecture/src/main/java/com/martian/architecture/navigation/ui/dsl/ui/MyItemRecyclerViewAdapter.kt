package com.martian.architecture.navigation.ui.dsl.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.martian.architecture.R
import com.martian.architecture.databinding.FragmentDslItemBinding

import com.martian.architecture.navigation.ui.dsl.ui.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private var listener:OnItemClickListener? = null
    private var smartListener : ((item:PlaceholderItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(FragmentDslItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
        holder.itemView.setOnClickListener(){
            listener?.onClickListener(item)
            smartListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentDslItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        this.listener = listener
    }

    fun setOnSmartItemClickListener(mSmartListener:(item:PlaceholderItem) -> Unit){
        this.smartListener = mSmartListener
    }


    interface OnItemClickListener{
        fun onClickListener(item:PlaceholderItem)
    }

}

