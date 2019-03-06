package com.kekyazilim.bookshelf.util.bookprocess

import com.kekyazilim.bookshelf.databaseprocess.AppRoomDatabase
import com.kekyazilim.bookshelf.util.categorybookjoinprocess.CategoryBookJoinDataManagement
import dagger.Module
import dagger.Provides

@Module
class BookProcessModule {

    @Provides
    fun provideBookTableModelGenerator(): BookTableModelGenerator {
        return BookTableModelGenerator()
    }

    @Provides
    fun provideBookDataManagement(apiRoomDatabase: AppRoomDatabase, bookTableModelGenerator: BookTableModelGenerator): BookDataManagement {
        return BookDataManagement(apiRoomDatabase.bookDao(), bookTableModelGenerator)
    }


}