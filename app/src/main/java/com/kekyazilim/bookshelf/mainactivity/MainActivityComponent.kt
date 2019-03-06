package com.kekyazilim.bookshelf.mainactivity

import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabaseModule
import com.kekyazilim.bookshelf.util.bookprocess.BookProcessModule
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinModule
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.intentprocess.IntentModule
import com.kekyazilim.bookshelf.util.windowprocess.WindowModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AppModule::class),
        (AppRoomDatabaseModule::class),
        (CategoryProcessModule::class),
        (BookProcessModule::class),
        (CategoryBookJoinModule::class),
        (WindowModule::class),
        (IntentModule::class)
    ]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

}