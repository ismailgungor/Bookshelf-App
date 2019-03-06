package com.kekyazilim.bookshelf.bookactivity

import android.content.Intent
import android.view.Window
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatEditText
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import io.reactivex.subjects.PublishSubject

interface BookActivityContract {

    interface View {

        fun setScreenHeight(height: Int)

        fun setScreenWidth(width: Int)

        fun showMainView()

        fun hideMainView()

        fun hideFabView()

        fun showFabView()

        fun startShowingCircularMainAnimation()

        fun startHidingCircularFabAnimation()

        fun startHidingCircularMainAnimation()

        fun showCategoryListDialog(clickEvent: PublishSubject<CategoryTable>, categoryList: ArrayList<CategoryTable>)

        fun addCategoryTagView(it: CategoryTable)

        fun hideDialog()

        fun initCategoryTagView()

        fun bindViews()

        fun showMissingNameMessage()

        fun showMissingAuthorMessage()

        fun showMissingPublishYearMessage()

        fun showMissingPriceMessage()

        fun showMissingCategoryMessage()

        fun startShowingCircularFabAnimation()

        fun finishBookActivity(intent: Intent)

        fun setNameArea(name: String)

        fun setAuthorArea(author: String)

        fun setPublishYearArea(year: String)

        fun setPriceArea(price: String)

        fun setRateArea(rate: Float)

        fun initCategoryTagViewWithList(list: List<CategoryTable>)

        fun setToolbarTitleForEdit()

    }

    interface Presenter {

        fun setView(view: View)

        fun created(intent: Intent)

        fun removeTransitionListener()

        fun backPressed()

        fun windowFocusChanged(mainWrapper: android.view.View)

        fun tagLayoutClicked()

        fun checkCategoryList()

        fun initClickEvent()

        fun saveClicked(etName: AppCompatEditText,
                        etAuthor: AppCompatEditText,
                        etPrice: AppCompatEditText,
                        etPublishYear: AppCompatEditText,
                        ratingBar: RatingBar,
                        chosenCategories: ArrayList<CategoryTable>)

        fun checkBookModel()

        fun updateView()

    }

}