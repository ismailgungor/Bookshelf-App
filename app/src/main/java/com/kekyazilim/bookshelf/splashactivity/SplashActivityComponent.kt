package com.kekyazilim.bookshelf.splashactivity

import com.kekyazilim.bookshelf.app.AppModule
import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabaseModule
import com.kekyazilim.bookshelf.util.categoryprocess.CategoryProcessModule
import com.kekyazilim.bookshelf.util.intentprocess.IntentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        (AppModule::class),
        (IntentModule::class),
        (AppRoomDatabaseModule::class),
        (CategoryProcessModule::class)]
)
interface SplashActivityComponent {

    fun inject(activity: SplashActivity)

}