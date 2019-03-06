package com.kekyazilim.bookshelf.util.itemclick

import android.view.View

interface IListItemClick {

	fun onItemClick(position: Int) {}

	fun <T> onItemClick(model: T) {}

	fun onItemLongClick() {}

	fun <T> onItemLongClick(model: T) {}

	fun <T> onItemEditClick(model: T) {}

	fun <T> onItemCheckClick(model: T, view: View)


}