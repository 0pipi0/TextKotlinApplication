package com.martian.architecture.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.martian.architecture.R
import com.martian.architecture.paging.data.Person

/**
 * Create by：Martian
 * on 2022/6/8
 */
class PageAdapter : PagedListAdapter<Person, PageAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tv_content)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = getItem(position)
        if (person != null) {
            holder.tv.text = "name:${person.name} age:${person.age}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_page, parent, false)
        return ViewHolder(view)
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Person> =
            object : DiffUtil.ItemCallback<Person>() {
                override fun areItemsTheSame(
                    oldConcert: Person,
                    newConcert: Person
                ): Boolean {
                    return oldConcert.name == newConcert.name
                }

                override fun areContentsTheSame(
                    oldConcert: Person,
                    newConcert: Person
                ): Boolean {
                    return oldConcert.equals(newConcert)
                }
            }
    }


}