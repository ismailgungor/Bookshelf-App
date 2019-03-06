package com.kekyazilim.bookshelf.mainactivity

import android.content.Intent
import android.view.View
import com.github.clans.fab.FloatingActionButton
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import java.io.Serializable

interface MainActivityContract {

    interface View {

        fun bindViews()

        fun initOnClicks()

        fun showProgress()

        fun hideProgress()

        fun showEmptyListMessage()

        fun hideEmptyListMessage()

        fun showBookList(bookList: List<BookTable>)

        fun hideBookList()

        fun showFabProgress()

        fun hideFabProgress()

        fun showAddingBookProcessWasSuccessfulMessage()

        fun showAddingBookProcessWasFailedMessage()

        fun setFabClickListener()

        fun setFabClickListenerNull()

        fun initToolbarForNormalProcess()

        fun initToolbarForDeleteProcess()

        fun backPressed()

        fun notifyAdapterForChangeMode(deleteProcess: Boolean)

        fun updateSelectedText(count: Int)

        fun showCheckButton()

        fun hideCheckButton()

        fun showSelectedText()

        fun hideSelectedText()

        fun hideDeleteButton()

        fun showDeleteButton()

        fun showDeletingBookProcessWasSuccessful()

        fun showDeletingBookProcessWasFailed()

        fun showEditingBookProcessWasSuccessful()

        fun showEditingBookProcessWasFailed()

    }

    interface Presenter {

        fun setView(view: View)

        fun created()

        fun fabClicked(fab: FloatingActionButton)

        fun checkBookList()

        fun receivedBookModelToAdd(data: Intent?)

        fun addBookToDb(model: Serializable)

        fun deleteButtonClicked()

        fun backClicked()

        fun receivedCheckClick(bookTable: BookTable, view: android.view.View)

        fun checkClicked()

        fun receivedEditClick(fab: FloatingActionButton, bookTable: BookTable)

        fun receivedBookModelToEdit(data: Intent?)

        fun editBook(model: Serializable)
    }
}