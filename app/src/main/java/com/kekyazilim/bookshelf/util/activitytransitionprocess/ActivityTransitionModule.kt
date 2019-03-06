package com.kekyazilim.bookshelf.util.activitytransitionprocess

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityTransitionModule(activity: AppCompatActivity) {

    private var mActivity = activity

    @Provides
    fun provideActivityTransitionManager(): ActivityTransitionManager {
        return ActivityTransitionManager(mActivity)
    }

}