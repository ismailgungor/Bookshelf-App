package com.kekyazilim.bookshelf.mainactivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionButton
import com.kekyazilim.bookshelf.R
import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.rvadapter.BookListAdapter
import com.kekyazilim.bookshelf.util.bookprocess.BookProcessModule
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinModule
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.intentprocess.ActivityRequest
import com.kekyazilim.bookshelf.util.intentprocess.IntentModule
import com.kekyazilim.bookshelf.util.itemclick.IListItemClick
import com.kekyazilim.bookshelf.util.windowprocess.WindowModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityContract.View {

    lateinit var mFab: FloatingActionButton
    lateinit var rvBookList: RecyclerView

    lateinit var tvSelectedItems: AppCompatTextView
    lateinit var ivBack: AppCompatImageView
    lateinit var ivDelete: AppCompatImageView
    lateinit var tvAppName: AppCompatTextView
    lateinit var ivCheck: AppCompatImageView

    lateinit var llEmptyListMessage: LinearLayout
    lateinit var progressBar: ProgressBar
    private var mAdapter: BookListAdapter? = null

    @Inject
    lateinit var mPresenter: MainActivityPresenter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.builder()
            .appModule(AppModule(this))
            .categoryProcessModule(CategoryProcessModule())
            .bookProcessModule(BookProcessModule())
            .categoryBookJoinModule(CategoryBookJoinModule())
            .windowModule(WindowModule(this))
            .intentModule(IntentModule(this))
            .build().inject(this)

        bindViews()
        initOnClicks()

        this.mPresenter.setView(this)
        this.mPresenter.created()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ActivityRequest.ADD_BOOK -> mPresenter.receivedBookModelToAdd(data)
                ActivityRequest.EDIT_BOOK -> mPresenter.receivedBookModelToEdit(data)
            }
        }

    }

    override fun bindViews() {
        this.mFab = findViewById(R.id.fab_add_book)
        this.rvBookList = findViewById(R.id.rv_book_list)
        this.progressBar = findViewById(R.id.progress_bar)
        this.llEmptyListMessage = findViewById(R.id.ll_empty_list)
        this.tvSelectedItems = findViewById(R.id.tv_selected)
        this.tvAppName = findViewById(R.id.tv_app_name)
        this.ivBack = findViewById(R.id.iv_back)
        this.ivDelete = findViewById(R.id.iv_remove)
        this.ivCheck = findViewById(R.id.iv_check)
    }

    override fun initToolbarForNormalProcess() {
        hideSelectedText()
        hideCheckButton()
        showDeleteButton()
        this.ivBack.visibility = View.GONE
        this.ivDelete.visibility = View.VISIBLE
        this.tvAppName.visibility = View.VISIBLE
    }

    override fun initToolbarForDeleteProcess() {
        this.ivBack.visibility = View.VISIBLE
        hideDeleteButton()
        this.tvAppName.visibility = View.GONE
    }

    override fun showCheckButton() {
        this.ivCheck.visibility = View.VISIBLE
    }

    override fun hideCheckButton() {
        this.ivCheck.visibility = View.GONE
    }

    override fun hideDeleteButton() {
        this.ivDelete.visibility = View.GONE

    }

    override fun showDeleteButton() {
        this.ivDelete.visibility = View.VISIBLE
    }

    override fun showSelectedText() {
        this.tvSelectedItems.visibility = View.VISIBLE
    }

    override fun hideSelectedText() {
        this.tvSelectedItems.visibility = View.GONE
    }

    override fun updateSelectedText(count: Int) {
        this.tvSelectedItems.text = getString(R.string.toolbar_selected_text, count)
    }

    override fun initOnClicks() {
        setFabClickListener()
        setBackListener()
        setDeleteListener()
        setCheckListener()
    }

    private fun setCheckListener() {
        this.ivCheck.setOnClickListener {
            this.mPresenter.checkClicked()
        }
    }

    private fun setDeleteListener() {
        this.ivDelete.setOnClickListener {
            this.mPresenter.deleteButtonClicked()
        }
    }

    private fun setBackListener() {
        this.ivBack.setOnClickListener {
            this.mPresenter.backClicked()
        }
    }

    override fun setFabClickListener() {
        this.mFab.setOnClickListener {
            this.mPresenter.fabClicked(mFab)
        }
    }

    override fun setFabClickListenerNull() {
        this.mFab.setOnClickListener(null)
    }

    override fun showFabProgress() {
        mFab.setShowProgressBackground(false)
        mFab.setIndeterminate(true)
    }

    override fun hideFabProgress() {
        mFab.setIndeterminate(false)
    }

    override fun showProgress() {
        this.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        this.progressBar.visibility = View.GONE
    }

    override fun showEmptyListMessage() {
        this.llEmptyListMessage.visibility = View.VISIBLE
    }

    override fun hideEmptyListMessage() {
        this.llEmptyListMessage.visibility = View.GONE
    }

    override fun showBookList(bookList: List<BookTable>) {
        this.rvBookList.layoutManager = LinearLayoutManager(this)
        this.mAdapter = BookListAdapter(this, bookList as ArrayList<BookTable>, object : IListItemClick {
            override fun <T> onItemEditClick(model: T) {
                mPresenter.receivedEditClick(mFab, model as BookTable)
            }

            override fun <T> onItemCheckClick(model: T, view: View) {
                mPresenter.receivedCheckClick(model as BookTable, view)

            }
        })
        this.rvBookList.adapter = mAdapter
        this.rvBookList.visibility = View.VISIBLE
    }

    override fun notifyAdapterForChangeMode(deleteProcess: Boolean) {
        this.mAdapter?.setDeleteMode(deleteProcess)
    }

    override fun hideBookList() {
        this.rvBookList.visibility = View.GONE
    }

    override fun showAddingBookProcessWasSuccessfulMessage() {
        showToast(getString(R.string.adding_book_was_successful))
    }

    override fun showAddingBookProcessWasFailedMessage() {
        showToast(getString(R.string.adding_book_was_failed))
    }

    override fun showEditingBookProcessWasSuccessful() {
        showToast(getString(R.string.editing_book_was_successful))
    }

    override fun showEditingBookProcessWasFailed() {
        showToast(getString(R.string.editing_book_was_failed))
    }

    override fun showDeletingBookProcessWasSuccessful() {
        showToast(getString(R.string.deleting_book_was_successful))
    }

    override fun showDeletingBookProcessWasFailed() {
        showToast(getString(R.string.deleting_book_was_failed))
    }

    override fun backPressed() {
        super.onBackPressed()
    }

    override fun onBackPressed() {
        this.mPresenter.backClicked()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}
