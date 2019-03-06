package com.kekyazilim.bookshelf.databaseprocess

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides

@Module
class AppRoomDatabaseModule {

    @Provides
    fun provideAppRoomDatabase(context: Context): AppRoomDatabase {
        return Room.databaseBuilder(
            context,
            AppRoomDatabase::class.java,
            DatabaseKeys.DATABASE_NAME
        ).build()
    }

}