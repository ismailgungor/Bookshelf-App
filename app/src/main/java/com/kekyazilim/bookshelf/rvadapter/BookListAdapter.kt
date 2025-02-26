package com.kekyazilim.bookshelf.rvadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kekyazilim.bookshelf.android.R
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.util.itemclick.IListItemClick
import com.kekyazilim.bookshelf.viewholder.BookListItemViewHoler

class BookListAdapter(
		context: Context,
		bookList: ArrayList<BookTable>,
		iListItemClick: IListItemClick
) : RecyclerView.Adapter<BookListItemViewHoler>() {

	private var mContext = context
	private var mBookList = bookList
	private var mIListITemClick = iListItemClick
	private var isDeleteMode = false

	fun setBookList(bookList: ArrayList<BookTable>) {
		this.mBookList = bookList
		notifyDataSetChanged()
	}

	fun setDeleteMode(isDeleteMode: Boolean) {
		this.isDeleteMode = isDeleteMode
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListItemViewHoler {
		val view = LayoutInflater.from(mContext)
				.inflate(R.layout.item_book, parent, false)

		return BookListItemViewHoler(view)
	}

	override fun onBindViewHolder(holder: BookListItemViewHoler, position: Int) {
		val currentModel = mBookList[position]

		currentModel.name.let {
			holder.setName(it)
		}

		currentModel.author.let {
			holder.setAuthor(it)
		}

		currentModel.price.let {
			val stAmount = it.toString()

			val concatPrice = if ((stAmount[stAmount.length - 1]) == '0') {
				stAmount.substring(0, stAmount.length - 2)
			} else {
				stAmount
			}
			holder.setPrice("$concatPrice TL")
		}

		currentModel.rate.let {
			holder.setRate(it.toString())
		}

		if (isDeleteMode) {
			holder.initDeleteMode()
			holder.cbBook.setOnClickListener {
				mIListITemClick.onItemCheckClick(currentModel, holder.cbBook)
			}
		} else {
			holder.initEditMode()
			holder.ivEdit.setOnClickListener {
				mIListITemClick.onItemEditClick(currentModel)
			}
		}

	}

	override fun getItemCount(): Int {
		return this.mBookList.size
	}


}