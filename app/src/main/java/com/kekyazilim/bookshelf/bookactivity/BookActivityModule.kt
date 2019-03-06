package com.kekyazilim.bookshelf.bookactivity

import android.content.Context
import com.kekyazilim.bookshelf.util.categorytaglistview.CategoryTagListViewHelper
import com.kekyazilim.bookshelf.util.customdialog.customcategorylistdialog.CategoryListDialogHelper
import com.kekyazilim.bookshelf.util.snackbar.SnackbarHelper
import dagger.Module
import dagger.Provides

@Module
class BookActivityModule {

    @Provides
    fun provideCategoryListDialogHelper(context: Context): CategoryListDialogHelper {
        return CategoryListDialogHelper(context)
    }

    @Provides
    fun provideCategoryTagListViewHelper(context: Context): CategoryTagListViewHelper {
        return CategoryTagListViewHelper(context)
    }

    @Provides
    fun provideSnackbarHelper(context: Context): SnackbarHelper {
        return SnackbarHelper(context)
    }

}