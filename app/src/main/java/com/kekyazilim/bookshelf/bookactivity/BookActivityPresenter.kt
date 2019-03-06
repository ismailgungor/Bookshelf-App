package com.kekyazilim.bookshelf.bookactivity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.transition.Transition
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatEditText
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import com.kekyazilim.bookshelf.model.BookModel
import com.kekyazilim.bookshelf.util.activitytransitionprocess.ActivityTransitionManager
import com.kekyazilim.bookshelf.util.activitytransitionprocess.TransitionCallback
import com.kekyazilim.bookshelf.util.bookprocess.BookDataManagement
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinDataManagement
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryDataManagement
import com.kekyazilim.bookshelf.util.intentprocess.BundleArgumentsHelper
import com.kekyazilim.bookshelf.util.intentprocess.BundleKeys
import com.kekyazilim.bookshelf.util.windowprocess.WindowManagerHelper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class BookActivityPresenter @Inject constructor() : BookActivityContract.Presenter {

    private lateinit var mView: BookActivityContract.View
    private lateinit var mCategoryList: ArrayList<CategoryTable>
    private lateinit var mTagAdapterClickEvent: PublishSubject<CategoryTable>
    private var mCurrentBook: BookTable? = null

    @Inject
    lateinit var mActivityTransitionManager: ActivityTransitionManager

    @Inject
    lateinit var mCategoryDataManagement: CategoryDataManagement

    @Inject
    lateinit var mWindowManagerHelper: WindowManagerHelper

    @Inject
    lateinit var mBundleArgumentsHelper: BundleArgumentsHelper

    @Inject
    lateinit var mCategoryBookJoinDataManagement: CategoryBookJoinDataManagement

    @Inject
    lateinit var mBookDataManagement: BookDataManagement


    override fun setView(view: BookActivityContract.View) {
        this.mView = view
    }

    override fun created(intent: Intent) {
        this.mBundleArgumentsHelper.setArguments(intent.extras)
        this.mWindowManagerHelper.setTransparentStatusBar()

        checkCategoryList()
        checkBookModel()
        initClickEvent()
        updateView()

        mActivityTransitionManager.setEnterTransitionNull()
        mActivityTransitionManager.setSharedElementEnterTransition(object : TransitionCallback {
            override fun onTransitionEnd(transition: Transition?) {
                mView.showMainView()
                mView.startHidingCircularFabAnimation()
                mView.startShowingCircularMainAnimation()
                mView.initCategoryTagView()
            }

        })
    }

    @SuppressLint("CheckResult")
    override fun checkCategoryList() {
        this.mCategoryDataManagement.getAllICategoriesFromDB()
            .subscribeOn(Schedulers.io())
            ?.subscribe({ this.mCategoryList = it as ArrayList<CategoryTable> }, {})
    }

    override fun checkBookModel() {
        this.mBundleArgumentsHelper.getSerializableModel(BundleKeys.BOOK_MODEL)?.let {
            mCurrentBook = it as BookTable
        }
    }

    @SuppressLint("CheckResult")
    override fun updateView() {
        mCurrentBook?.let {
            Single.just(mCurrentBook)
                .map { bookTable ->
                    mCategoryBookJoinDataManagement.getCategoryByBook(bookTable.bookId!!)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe({
                            mView.initCategoryTagViewWithList(it)
                        }, {})

                    bookTable
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.mView.setToolbarTitleForEdit()
                    this.mView.setNameArea(it.name)
                    this.mView.setAuthorArea(it.author)
                    this.mView.setPublishYearArea(it.publishDate.toString())

                    val stAmount = it.price.toString()

                    val concatPrice = if ((stAmount[stAmount.length - 1]) == '0') {
                        stAmount.substring(0, stAmount.length - 2)
                    } else {
                        stAmount
                    }
                    this.mView.setPriceArea(concatPrice)
                    this.mView.setRateArea(it.rate)
                }, {})
        }

    }

    @SuppressLint("CheckResult")
    override fun initClickEvent() {
        this.mTagAdapterClickEvent = PublishSubject.create<CategoryTable>()
        mTagAdapterClickEvent.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                this.mView.addCategoryTagView(it)
                this.mView.hideDialog()
            }
    }

    override fun windowFocusChanged(mainWrapper: View) {
        mView.setScreenHeight(mainWrapper.height)
        mView.setScreenWidth(mainWrapper.width)
    }

    override fun backPressed() {
        this.mView.startHidingCircularMainAnimation()
        this.mView.startShowingCircularFabAnimation()
    }

    override fun removeTransitionListener() {
        this.mActivityTransitionManager.removeTransitionListenerFromSharedElementEnterTransition()
    }

    override fun tagLayoutClicked() {
        this.mView.showCategoryListDialog(mTagAdapterClickEvent, mCategoryList)
    }

    override fun saveClicked(
        etName: AppCompatEditText,
        etAuthor: AppCompatEditText,
        etPrice: AppCompatEditText,
        etPublishYear: AppCompatEditText,
        ratingBar: RatingBar,
        chosenCategories: ArrayList<CategoryTable>
    ) {

        val stName = etName.text?.toString()?.trim()
        val stAuthor = etAuthor.text?.toString()?.trim()
        val stPrice = etPrice.text?.toString()?.trim()
        val stPublishYear = etPublishYear.text?.toString()?.trim()
        val rate = ratingBar.rating

        if (stName.isNullOrEmpty()) {
            mView.showMissingNameMessage()
            return
        }

        if (stAuthor.isNullOrEmpty()) {
            mView.showMissingAuthorMessage()
            return
        }

        if (stPublishYear.isNullOrEmpty()) {
            mView.showMissingPublishYearMessage()
            return
        }

        if (stPrice.isNullOrEmpty()) {
            mView.showMissingPriceMessage()
            return
        }

        if (chosenCategories.isEmpty()) {
            mView.showMissingCategoryMessage()
            return
        }

        val bookModel = this.mBookDataManagement.getConvertedBookModel(
            mCurrentBook?.bookId,
            stName,
            stAuthor,
            rate,
            stPrice,
            stPublishYear,
            chosenCategories
        )


        val bundle = Bundle()
        bundle.putSerializable(BundleKeys.BOOK_MODEL, bookModel)
        val intent = Intent()
        intent.putExtras(bundle)
        this.mView.finishBookActivity(intent)
        backPressed()

    }
}