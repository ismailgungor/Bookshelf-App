package com.kekyazilim.bookshelf.bookactivity

import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabaseModule
import com.kekyazilim.bookshelf.util.activitytransitionprocess.ActivityTransitionModule
import com.kekyazilim.bookshelf.util.animationprocess.AnimationModule
import com.kekyazilim.bookshelf.util.bookprocess.BookProcessModule
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinModule
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.windowprocess.WindowModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [(AppModule::class),
            (AnimationModule::class),
            (ActivityTransitionModule::class),
            (WindowModule::class),
            (AppRoomDatabaseModule::class),
            (CategoryProcessModule::class),
            (CategoryBookJoinModule::class),
            (BookProcessModule::class),
            (BookActivityModule::class)]
)
interface BookActivityComponent {

    fun inject(activity: BookActivity)

}