package com.kekyazilim.bookshelf.databaseprocess.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.kekyazilim.bookshelf.databaseprocess.DatabaseQueries
import com.kekyazilim.bookshelf.databaseprocess.table.BookTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryBookJoinTable
import com.kekyazilim.bookshelf.databaseprocess.table.CategoryTable
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CategoryBookJoinDao {


    @Insert(onConflict = REPLACE)
    fun insert(categoryBookJoinTable: CategoryBookJoinTable): Single<Long>

    @Insert(onConflict = REPLACE)
    fun insertAll(categoryBookJoinTableList: List<CategoryBookJoinTable>): Completable

    @Delete
    fun deleteAll(categoryBookJoinTableList: List<CategoryBookJoinTable>): Completable

    @Query(DatabaseQueries.GET_CATEGORIES_FOR_BOOK)
    fun getCategoriesForBook(bookId: Int): Single<List<CategoryTable>>

    @Query(DatabaseQueries.GET_BOOKS_FOR_CATEGORY)
    fun getBooksForCategory(categoryId: Int): Single<List<BookTable>>

    @Query(DatabaseQueries.DELETE_ITEM_BY_BOOK_ID)
    fun deleteItemByBookId(bookId: Int)


}