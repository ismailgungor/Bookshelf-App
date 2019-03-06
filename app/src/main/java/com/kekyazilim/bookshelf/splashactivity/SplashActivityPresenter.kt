package com.kekyazilim.bookshelf.splashactivity

import android.annotation.SuppressLint
import android.os.Handler
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryDataManagement
import com.kekyazilim.bookshelf.util.intentprocess.IntentHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashActivityPresenter @Inject constructor() : SplashActivityContract.Presenter {

    private lateinit var mView: SplashActivityContract.View

    @Inject
    lateinit var mCategoryDataManagement: CategoryDataManagement

    @Inject
    lateinit var mIntentHelper: IntentHelper

    override fun setView(view: SplashActivityContract.View) {
        this.mView = view
    }

    @SuppressLint("CheckResult")
    override fun created() {

        Handler().postDelayed({
            this.mCategoryDataManagement.getAllCategoriesFromFile()?.let { categoryListFromFile ->

                if (categoryListFromFile.isNotEmpty()) {
                    mCategoryDataManagement.getCategoryCountFromDB()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if (it < categoryListFromFile.size) {
                                mCategoryDataManagement.deleteAll()?.andThen(
                                    mCategoryDataManagement.insertAll(categoryListFromFile)
                                )?.subscribeOn(Schedulers.io())
                                    ?.observeOn(AndroidSchedulers.mainThread())
                                    ?.subscribe({
                                        continueWithMainActivity()

                                    }, {
                                        continueWithError()
                                    })

                            } else {
                                continueWithMainActivity()
                            }

                        }, {
                            continueWithError()
                        })
                } else
                    continueWithError()

            }
        }, 1000)


    }

    private fun continueWithError() {
        this.mView.showErrorMessage()
    }

    private fun continueWithMainActivity() {
        mIntentHelper.intentMainActivity()
        mView.finishActivity()
    }
}