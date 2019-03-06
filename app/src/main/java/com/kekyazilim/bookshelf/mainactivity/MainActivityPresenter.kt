package com.kekyazilim.bookshelf.mainactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import com.github.clans.fab.FloatingActionButton
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryBookJoinTable
import com.kekyazilim.bookshelf.model.BookModel
import com.kekyazilim.bookshelf.util.bookprocess.BookDataManagement
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinDataManagement
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryDataManagement
import com.kekyazilim.bookshelf.util.intentprocess.BundleKeys
import com.kekyazilim.bookshelf.util.intentprocess.IntentHelper
import com.kekyazilim.bookshelf.util.windowprocess.WindowManagerHelper
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.Serializable
import java.lang.Exception
import javax.inject.Inject

class MainActivityPresenter @Inject constructor() : MainActivityContract.Presenter {

    private lateinit var mView: MainActivityContract.View
    private var isDeleteProcess = false
    private var mChosenDeleteList = ArrayList<BookTable>()

    @Inject
    lateinit var mBookDataManagement: BookDataManagement

    @Inject
    lateinit var mCategoryBookJoinDataManagement: CategoryBookJoinDataManagement

    @Inject
    lateinit var mIntentHelper: IntentHelper

    @Inject
    lateinit var mWindowManagerHelper: WindowManagerHelper


    override fun setView(view: MainActivityContract.View) {
        this.mView = view
    }

    override fun created() {
        this.mWindowManagerHelper.setTransparentStatusBar()
        this.mView.initToolbarForNormalProcess()
        this.mView.showProgress()
        checkBookList()

    }

    @SuppressLint("CheckResult")
    override fun checkBookList() {
        this.mBookDataManagement.getAllItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isNotEmpty()) {
                    this.mView.hideProgress()
                    this.mView.hideEmptyListMessage()
                    this.mView.showBookList(it)
                    this.mView.showDeleteButton()
                } else {
                    initEmptyView()
                }
            }, {
                initEmptyView()
            })
    }

    private fun initEmptyView() {
        this.mView.hideProgress()
        this.mView.showEmptyListMessage()
        this.mView.hideBookList()
        this.mView.hideDeleteButton()
    }

    override fun fabClicked(fab: FloatingActionButton) {
        this.mIntentHelper.intentBookActivity(fab, null)
    }

    override fun receivedBookModelToAdd(data: Intent?) {
        val serializable = data?.extras?.getSerializable(BundleKeys.BOOK_MODEL)
        serializable?.let {
            this.mView.setFabClickListenerNull()
            this.mView.showFabProgress()
            this.addBookToDb(it)
        }
    }

    override fun receivedBookModelToEdit(data: Intent?) {
        val serializable = data?.extras?.getSerializable(BundleKeys.BOOK_MODEL)
        serializable?.let {
            this.mView.setFabClickListenerNull()
            this.mView.showFabProgress()
            this.editBook(it)
        }
    }

    @SuppressLint("CheckResult")
    override fun addBookToDb(model: Serializable) {
        val bookModel = model as BookModel

        val bookTable = this.mBookDataManagement.getConvertedBookTable(bookModel)

        this.mBookDataManagement.insert(bookTable)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .map { bookId ->

                var flag = true
                val joinTableList = ArrayList<CategoryBookJoinTable>()
                for (category in bookModel.categoryList!!) {
                    joinTableList.add(CategoryBookJoinTable(category.categoryId!!, bookId.toInt()))
                }

                try {
                    mCategoryBookJoinDataManagement.insertAll(joinTableList).blockingGet()
                } catch (e: Exception) {
                    flag = false
                }

                flag
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.mView.hideFabProgress()
                this.mView.setFabClickListener()
            }
            .subscribe({
                if (it)
                    this.mView.showAddingBookProcessWasSuccessfulMessage()
                else
                    this.mView.showAddingBookProcessWasFailedMessage()

            }, {
                this.mView.showAddingBookProcessWasFailedMessage()
            })
    }

    @SuppressLint("CheckResult")
    override fun editBook(model: Serializable) {
        val bookModel = model as BookModel
        val bookId = bookModel.bookId!!

        val bookTable = this.mBookDataManagement.getConvertedBookTable(bookModel)


        val joinTableList = ArrayList<CategoryBookJoinTable>()
        for (category in bookModel.categoryList!!) {
            joinTableList.add(CategoryBookJoinTable(category.categoryId!!, bookId))
        }

        this.mCategoryBookJoinDataManagement.deleteItemByBookId(bookId)
            .andThen(this.mBookDataManagement.update(bookTable))
            .andThen(mCategoryBookJoinDataManagement.insertAll(joinTableList))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.mView.hideFabProgress()
                this.mView.setFabClickListener()
            }
            .subscribe({
                this.mView.showEditingBookProcessWasSuccessful()
            },
                {
                    this.mView.showEditingBookProcessWasFailed()
                })


    }

    override fun deleteButtonClicked() {
        this.mView.initToolbarForDeleteProcess()
        this.isDeleteProcess = true
        this.mView.notifyAdapterForChangeMode(isDeleteProcess)
    }

    override fun backClicked() {
        if (isDeleteProcess) {
            this.mView.initToolbarForNormalProcess()
            isDeleteProcess = false
            this.mView.notifyAdapterForChangeMode(isDeleteProcess)
            this.mChosenDeleteList = ArrayList()
            this.mView.updateSelectedText(mChosenDeleteList.size)
        } else
            this.mView.backPressed()
    }

    override fun receivedCheckClick(bookTable: BookTable, view: View) {
        val checkBox = view as AppCompatCheckBox
        if (!checkBox.isChecked) {
            this.mChosenDeleteList.remove(bookTable)
        } else
            this.mChosenDeleteList.add(bookTable)


        if (mChosenDeleteList.isEmpty()) {
            this.mView.hideSelectedText()
            this.mView.hideCheckButton()
        } else {
            this.mView.showCheckButton()
            this.mView.showSelectedText()
            this.mView.updateSelectedText(mChosenDeleteList.size)
        }
    }

    @SuppressLint("CheckResult")
    override fun checkClicked() {
        isDeleteProcess = false
        this.mView.notifyAdapterForChangeMode(isDeleteProcess)
        this.mView.initToolbarForNormalProcess()
        mView.showFabProgress()

        if (mChosenDeleteList.size == 1) {
            subscribeForDeleteBookProcess(mBookDataManagement.delete(mChosenDeleteList.first()))

        } else {
            subscribeForDeleteBookProcess(mBookDataManagement.deleteAll(mChosenDeleteList))
        }
    }

    override fun receivedEditClick(fab: FloatingActionButton, bookTable: BookTable) {
        this.mIntentHelper.intentBookActivity(fab, bookTable)
    }

    @SuppressLint("CheckResult")
    private fun subscribeForDeleteBookProcess(complete: Completable) {
        complete.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                this.mView.hideFabProgress()
                this.mChosenDeleteList = ArrayList()
            }
            .subscribe({
                this.mView.showDeletingBookProcessWasSuccessful()
            }, {
                this.mView.showDeletingBookProcessWasFailed()
            })

    }
}