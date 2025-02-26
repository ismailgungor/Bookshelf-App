package com.kekyazilim.bookshelf.viewholder


import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.kekyazilim.bookshelf.android.R

class BookListItemViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

	private lateinit var tvName: AppCompatTextView
	private lateinit var tvAuthor: AppCompatTextView
	private lateinit var tvPrice: AppCompatTextView
	private lateinit var tvRate: AppCompatTextView
	lateinit var ivEdit: AppCompatImageView
	lateinit var cbBook: AppCompatCheckBox

	init {
		bindViews(itemView)
	}


	fun setName(name: String) {
		this.tvName.text = name
	}

	fun setAuthor(author: String) {
		this.tvAuthor.text = author
	}

	fun setPrice(price: String) {
		this.tvPrice.text = price
	}

	fun setRate(rate: String) {
		this.tvRate.text = rate
	}

	fun initDeleteMode() {
		this.ivEdit.visibility = View.GONE
		this.cbBook.visibility = View.VISIBLE
	}

	fun initEditMode() {
		this.ivEdit.visibility = View.VISIBLE
		this.cbBook.visibility = View.GONE
		this.cbBook.isChecked = false
	}

	private fun bindViews(itemView: View) {
		this.tvName = itemView.findViewById(R.id.tv_name)
		this.tvAuthor = itemView.findViewById(R.id.tv_author)
		this.tvPrice = itemView.findViewById(R.id.tv_price)
		this.tvRate = itemView.findViewById(R.id.tv_rate)
		this.ivEdit = itemView.findViewById(R.id.iv_edit)
		this.cbBook = itemView.findViewById(R.id.cb_book)
	}


}