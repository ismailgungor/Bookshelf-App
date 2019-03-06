package com.kekyazilim.bookshelf.util.categorytaglistview

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import com.kekyazilim.bookshelf.customview.CategoryTagItem
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable

class CategoryTagListViewHelper(context: Context) {

    private var mContext = context
    private var mChosenCategoryList = ArrayList<CategoryTable>()
    private lateinit var mRootView: LinearLayout
    private lateinit var ivAddCategory: AppCompatImageView

    fun init(rootView: LinearLayout, addCategoryView: AppCompatImageView) {
        this.mRootView = rootView
        this.ivAddCategory = addCategoryView
    }

    fun init(rootView: LinearLayout, addCategoryView: AppCompatImageView, initizialCategoryList: ArrayList<CategoryTable>) {
        this.mRootView = rootView
        this.ivAddCategory = addCategoryView
        this.mChosenCategoryList = initizialCategoryList
        invalidateView()
    }

    private fun invalidateView() {
        for (category in mChosenCategoryList)
            addView(category)
    }

    fun addCategoryTag(categoryTable: CategoryTable) {

        if (!mChosenCategoryList.contains(categoryTable)) {
            mChosenCategoryList.add(categoryTable)
            addView(categoryTable)
        }

    }

    fun addView(categoryTable: CategoryTable) {
        val tagItem = CategoryTagItem(mContext)
        tagItem.setName(categoryTable.name)
        tagItem.id = categoryTable.categoryId!!
        tagItem.mView.setOnClickListener {
            deleteChosenCategoryTag(categoryTable, tagItem)
        }
        mRootView.addView(tagItem.mView)
        if (mChosenCategoryList.size == 3)
            ivAddCategory.visibility = View.GONE
    }

    fun getChosenCategories(): ArrayList<CategoryTable> {
        return this.mChosenCategoryList
    }

    private fun deleteChosenCategoryTag(categoryTable: CategoryTable, tagItem: CategoryTagItem) {

        mRootView.removeView(tagItem.mView)
        mChosenCategoryList.remove(categoryTable)
        ivAddCategory.visibility = View.VISIBLE

    }

}