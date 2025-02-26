package com.kekyazilim.bookshelf.viewholder


import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kekyazilim.bookshelf.android.R

class CategoryTagListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var tvName: AppCompatTextView

    init {
        bindViews(itemView)
    }

    fun setName(name: String) {
        this.tvName.text = name
    }

    private fun bindViews(itemView: View) {
        this.tvName = itemView.findViewById(R.id.tv_name)
    }

}