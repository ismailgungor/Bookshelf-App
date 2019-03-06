package com.kekyazilim.bookshelf.rvadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kekyazilim.bookshelf.R
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.viewholder.CategoryTagListItemHolder
import io.reactivex.subjects.PublishSubject

class CategoryTagListAdapter(context: Context,
                             categoryList: ArrayList<CategoryTable>,
                             clickEvent: PublishSubject<CategoryTable>)
    : RecyclerView.Adapter<CategoryTagListItemHolder>() {

    private var mContext = context
    private var mCategoryList = categoryList
    private val mClickEvent = clickEvent


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryTagListItemHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_category_tag_list, parent,false)

        return CategoryTagListItemHolder(view)
    }



    override fun onBindViewHolder(holder: CategoryTagListItemHolder, position: Int) {
        val currentModel = mCategoryList[position]

        holder.setName(currentModel.name)
        holder.itemView.setOnClickListener{
            mClickEvent.onNext(currentModel)
        }
    }

    override fun getItemCount(): Int {
        return this.mCategoryList.size
    }
}