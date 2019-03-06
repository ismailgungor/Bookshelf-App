package com.kekyazilim.bookshelf.app

import android.content.Context
import com.kekyazilim.bookshelf.util.intentprocess.BundleArgumentsHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule(context: Context) {

    private var mContext = context


    @Provides
    fun provideContext(): Context {
        return this.mContext
    }

    @Provides
    fun provideBundleArgumentsHelper(): BundleArgumentsHelper {
        return BundleArgumentsHelper()
    }

}