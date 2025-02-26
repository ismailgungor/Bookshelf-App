package com.kekyazilim.bookshelf.util.customdialog.customcategorylistdialog

import android.content.Context

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.kekyazilim.bookshelf.android.R
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.rvadapter.CategoryTagListAdapter
import com.kekyazilim.bookshelf.util.customdialog.IBaseCustomDialogHelper
import io.reactivex.subjects.PublishSubject

class CategoryListDialogHelper(context: Context) : IBaseCustomDialogHelper {

    private var mContext = context
    private var mCategoryListDialog: MaterialDialog? = null
    private var mRootCategoryList: ArrayList<CategoryTable>? = null
    private var mSearchedCategoryList = ArrayList<CategoryTable>()
    private var mAdapter: CategoryTagListAdapter? = null
    private lateinit var mClickEvent: PublishSubject<CategoryTable>


    lateinit var etSearch: AppCompatEditText
    lateinit var rvCategoryList: RecyclerView
    lateinit var tvNoResult: AppCompatTextView

    fun setCategoryList(categoryList: ArrayList<CategoryTable>): IBaseCustomDialogHelper {
        this.mRootCategoryList = categoryList
        this.mSearchedCategoryList = categoryList.clone() as ArrayList<CategoryTable>
        return this
    }

    override fun <T> setClickEvent(clickEvent: PublishSubject<T>): IBaseCustomDialogHelper {
        this.mClickEvent = clickEvent as PublishSubject<CategoryTable>
        return this
    }

    override fun prepareCustomDialog(): IBaseCustomDialogHelper {
        this.mCategoryListDialog = MaterialDialog.Builder(mContext)
                .customView(R.layout.dialog_category_list, false)
                .build()

        handleDialogView()
        return this
    }

    override fun handleDialogView() {

        val view = this.mCategoryListDialog?.customView

        view?.let {
            bindViews(it)
        }

        rvCategoryList.layoutManager = LinearLayoutManager(mContext)
        mAdapter = CategoryTagListAdapter(mContext, mSearchedCategoryList, mClickEvent)
        rvCategoryList.adapter = mAdapter
    }

    private fun bindViews(view: View) {
        this.etSearch = view.findViewById(R.id.et_search_category)
        this.rvCategoryList = view.findViewById(R.id.lv_category_list)
        this.tvNoResult = view.findViewById(R.id.tv_no_result)

        this.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                mSearchedCategoryList.clear()

                mRootCategoryList?.let { categoryList ->

                    editable?.isNotEmpty()?.let {

                        for (index in categoryList.indices) {
                            val currentTag = categoryList[index]
                            if (currentTag.name.toLowerCase().contains(editable.toString().toLowerCase()))
                                mSearchedCategoryList.add(currentTag)
                        }

                    }
                            ?: kotlin.run { mSearchedCategoryList = categoryList.clone() as ArrayList<CategoryTable> }


                }

                if (mSearchedCategoryList.isNotEmpty()) {
                    updateCategoryList()
                    showLvCategoryList()
                } else
                    showNoResultText()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun updateCategoryList() {
        mAdapter?.notifyDataSetChanged()
    }

    private fun showLvCategoryList() {
        this.rvCategoryList.visibility = View.VISIBLE
        this.tvNoResult.visibility = View.GONE
    }

    private fun showNoResultText() {
        this.rvCategoryList.visibility = View.GONE
        this.tvNoResult.visibility = View.VISIBLE
    }


    override fun showCustomDialog(): IBaseCustomDialogHelper {
        this.mCategoryListDialog?.show()
        return this
    }

    override fun dismissCustomDialog(): IBaseCustomDialogHelper {
        this.mCategoryListDialog?.dismiss()
        return this
    }
}