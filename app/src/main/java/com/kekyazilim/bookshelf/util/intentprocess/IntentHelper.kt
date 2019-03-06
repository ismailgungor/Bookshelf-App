package com.kekyazilim.bookshelf.util.intentprocess

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.github.clans.fab.FloatingActionButton
import com.kekyazilim.bookshelf.bookactivity.BookActivity
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.mainactivity.MainActivity
import com.kekyazilim.bookshelf.model.BookModel

class IntentHelper(activity: Activity) {

    private val mActivity = activity

    fun intentMainActivity() {
        val intent = Intent(mActivity, MainActivity::class.java)
        mActivity.startActivity(intent)
    }

    fun intentBookActivity(view: FloatingActionButton, bookModel: BookTable?) {
        var requestType = ActivityRequest.ADD_BOOK
        val intent = Intent(mActivity, BookActivity::class.java)

        bookModel?.let {
            requestType = ActivityRequest.EDIT_BOOK
            val bundle = Bundle()
            bundle.putSerializable(BundleKeys.BOOK_MODEL, it)
            intent.putExtras(bundle)
        }

        var options: ActivityOptions? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(
                    mActivity,
                    android.util.Pair.create<View, String>(view, "fab")
            )
        }

        mActivity.startActivityForResult(intent, requestType, options?.toBundle())
    }


}