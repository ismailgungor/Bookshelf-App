package com.kekyazilim.bookshelf.util.windowprocess

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class WindowModule(activity: AppCompatActivity) {

    private var mActivity = activity

    @Provides
    fun provideWindowManagerHelper(): WindowManagerHelper {
        return WindowManagerHelper(mActivity)
    }

}