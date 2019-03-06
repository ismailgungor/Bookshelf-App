package com.kekyazilim.bookshelf.customview

import android.content.Context

import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.kekyazilim.bookshelf.R

class CategoryTagItem(context: Context) {

    lateinit var mView: LinearLayout
    private var mContext: Context = context

    lateinit var tvName: AppCompatTextView
    lateinit var ivRemove: AppCompatImageView
    var id: Int = 0

    init {
        initView()
    }


    private fun initView() {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        mView = inflater.inflate(R.layout.item_category_tag, null) as LinearLayout
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

        val marginValue = mContext.resources.getDimensionPixelOffset(R.dimen.tag_item_margin)
        params.setMargins(marginValue, marginValue, marginValue, marginValue)
        mView.layoutParams = params
        bindViews(mView)


    }

    private fun bindViews(view: View) {
        this.tvName = view.findViewById(R.id.tv_name)
        this.ivRemove = view.findViewById(R.id.iv_remove)
    }

    fun setName(name: String) {
        this.tvName.text = name
    }
}